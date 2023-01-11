package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Exceptions.NoSuchSemesterException;
import com.ErasmusApplication.ErasmusApp.Models.*;
import com.ErasmusApplication.ErasmusApp.Payload.PlacementManagerResponse;
import com.ErasmusApplication.ErasmusApp.Repositories.PlacementManagerRepository;
import com.ErasmusApplication.ErasmusApp.Repositories.WaitingBinRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class PlacementManagerService {

  // Properties
  StudentService studentService;
  HostUniversityService hostUniversityService;
  HostUniversityDepartmentService hostUniversityDepartmentService;
  UserClassService userClassService;
  PlacementTableService placementTableService;
  WaitingBinService waitingBinService;
  PlacementManagerRepository placementManagerRepository;
  private final WaitingBinRepository waitingBinRepository;

  // Methods

  /* CONTRACT:
    PRE-CONDITIONS:
      * International Students Office uploads a valid Excel file.
      * The academic year of the score table is known and passed as parameter in the form YEAR-YEAR/SEMESTER (e.g. 2022-2023/FALL).
      * The application type is known and passed as parameter (ERASMUS or EXCHANGE).
      * The column order is correct and is the same with the sample score table on https://www.cs.bilkent.edu.tr/~calkan/erasmus/.
    POST-CONDITIONS:
      * List of all Application objects are returned which will be used in placeStudents() function.
  */
  public List<Application> importApplicationsFromExcel( MultipartFile reapExcelDataFile, String academicYear, String applicationType) throws NoSuchSemesterException, IOException {

    List<Application> applicationList = new ArrayList<Application>();
    XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
    XSSFSheet worksheet = workbook.getSheetAt(0);
    DataFormatter formatter = new DataFormatter();

    for( int i=1; i<worksheet.getPhysicalNumberOfRows(); i++ ) {

      Student student;
      String schoolId;
      double totalPoints;
      String hostUniversityName;
      String durationPreferred;
      String standardizedSemesterCode;
      List<String> preferredUniversities = new ArrayList<>();

      XSSFRow row = worksheet.getRow(i);

      // Get student id & create student instance.
      schoolId = formatter.formatCellValue( row.getCell(3) );
      if( schoolId.equals("") ){
        break;
      }
      student = studentService.getStudentBySchoolId(schoolId);

      // Get total points.
      totalPoints = row.getCell(20).getNumericCellValue();

      // Get preferred semester.
      durationPreferred = row.getCell(21).getStringCellValue();
      if( durationPreferred.equals("SPRING")) {
        standardizedSemesterCode = academicYear + "/SPRING";
      }
      else if(durationPreferred.equals("FALL") ) {
        standardizedSemesterCode = academicYear + "/FALL";
      }
      else {
        throw new NoSuchSemesterException("Could not match semester name in one of the rows in the Excel file.");
      }

      for( int j = 22; j < 27; j++ ){
        hostUniversityName = row.getCell(j).getStringCellValue();
        if( hostUniversityName.equals("") ){
          break;
        }
        preferredUniversities.add(hostUniversityName);
      }

      /*
      // Set preferred university #1.
      hostUniversityName = row.getCell(22).getStringCellValue();
      hostUniversity = new HostUniversity(hostUniversityName);
      preferredUniversities.add(hostUniversity);

      // Set preferred university #2.
      hostUniversityName = row.getCell(23).getStringCellValue();
      hostUniversity = new HostUniversity(hostUniversityName);
      preferredUniversities.add(hostUniversity);

      // Set preferred university #3.
      hostUniversityName = row.getCell(24).getStringCellValue();
      hostUniversity = new HostUniversity(hostUniversityName);
      preferredUniversities.add(hostUniversity);

      // Set preferred university #4.
      hostUniversityName = row.getCell(25).getStringCellValue();
      hostUniversity = new HostUniversity(hostUniversityName);
      preferredUniversities.add(hostUniversity);

      // Set preferred university #5.
      hostUniversityName = row.getCell(26).getStringCellValue();
      hostUniversity = new HostUniversity(hostUniversityName);
      preferredUniversities.add(hostUniversity);
      */


      // Create application object with parsed data.
      Application application = new Application(
        student, false, false, preferredUniversities,
        null, applicationType, totalPoints, standardizedSemesterCode );

      // Add application to student.
      student.addApplication(application);

      // Add application to the applicationList.
      applicationList.add(application);
    }
    return applicationList;
  }


  /* CONTRACT:
    PRE-CONDITIONS:
      * A list of all applications is passed as parameter which contains Application objects.
      * The abbreviated version of department name which represents the score table's department is passed as parameter (e.g. CS).
    POST-CONDITIONS:
      * List of two Lists are returned which contains the main list at index 0 and waiting bin at index 1.
  */
  public List<List<Application>> placeStudents( List<Application> allApplications, String departmentName ){

    List<Application> mainList = new ArrayList<>();
    List<Application> waitingBin = new ArrayList<>();
    List<List<Application>> combinedList = new ArrayList<>();

    List<HostUniversityDepartment> allUniversityDepartments;
    allUniversityDepartments = hostUniversityDepartmentService.getHostUniDeptByName( departmentName );

    Map<String, Integer> quotas = new HashMap<>();

    // 1. Get quotas of all universities with department "departmentName".
    for( int i = 0; i < allUniversityDepartments.size(); i++ ){
      String curHostUniName = allUniversityDepartments.get(i).getHostUniversity().getNameOfInstitution();
      //System.out.println(curHostUniName);
      int curQuota = allUniversityDepartments.get(i).getQuota();
      quotas.put( curHostUniName, curQuota );
    }

    // 2. Place students.
    for( int i = 0; i < allApplications.size(); i++ ){

      System.out.println("\nApplication number #" + i );

      List<String> curPreferredUniversities = allApplications.get(i).getPreferredUniversities();

      boolean placed = false;
      for( int j = 0; (j < curPreferredUniversities.size()) && !placed; j++ ){

        System.out.print( curPreferredUniversities.get(j) );

        int curQuota = quotas.get( curPreferredUniversities.get(j) );

        System.out.println( ", Quota: " + curQuota );

        if( curQuota > 0 ){

          // 2.T.1 Update quota.
          curQuota--;
          quotas.put( curPreferredUniversities.get(j), curQuota );

          // 2.T.2 Set placed university of the current application.
          hostUniversityService.connectApplicationHostUniversity(curPreferredUniversities.get(j), allApplications.get(i));
          System.out.println( "PLACED: " + curPreferredUniversities.get(j) );

          // 2.T.3 Add application to the main list.
          mainList.add( allApplications.get(i) );
          allApplications.get(i).setPlaced( true );
          allApplications.get(i).setInWaitingBin( false );

          placed = true;
        }
      }
      if( !placed ){
        // 3.F Add application to the waiting bin.
        waitingBin.add( allApplications.get(i) );
        allApplications.get(i).setPlaced( false );
        allApplications.get(i).setInWaitingBin( true );

      }
    }

    // 3. Add main list and waiting bin to the combined list which will be returned.
    combinedList.add(mainList);
    combinedList.add(waitingBin);
    return combinedList;
  }

  /* CONTRACT:
      PRE-CONDITIONS:
        * International Students Office uploads a valid Excel file.
        * The academic year of the score table is known and passed as parameter in the form YEAR-YEAR/SEMESTER (e.g. 2022-2023/FALL).
        * The application type is known and passed as parameter (ERASMUS or EXCHANGE).
        * The column order is correct and is the same with the sample score table on https://www.cs.bilkent.edu.tr/~calkan/erasmus/.
        * A list of all applications is passed as parameter which contains Application objects.
        * The abbreviated version of department name which represents the score table's department is passed as parameter (e.g. CS).
      POST-CONDITIONS:
        * List of two Lists are returned which contains the main list at index 0 and waiting bin at index 1.
    */
  public void getDataAndPlaceStudents( MultipartFile reapExcelDataFile, String academicYear, String applicationType, String departmentName ){

    List<Application> allApplications = new ArrayList<>();
    List<List<Application>> combinedList;
    List<DepartmentErasmusCoordinator> coordinators;

    // 1. Get all coordinators of the department.
    coordinators = userClassService.getCoordinatorsByDepartment( departmentName );
    PlacementManager placementManager = new PlacementManager();
    placementManagerRepository.save(placementManager);
    for (int i = 0; i < coordinators.size(); i++){
      coordinators.get(i).setPlacementManager(placementManager);
    }

    // 2. Create mainList and waitingBin and set managers.
    PlacementTable mainList = new PlacementTable();
    mainList.setPlacementManager(placementManager);
    mainList.setId(placementManager.getId());
    placementTableService.savePlacementTable(mainList);

    WaitingBin waitingBin = new WaitingBin();
    waitingBin.setPlacementManager(placementManager);
    waitingBin.setId(waitingBin.getId());
    waitingBinService.saveWaitingBin(waitingBin);
    System.out.println( "Main List: " + mainList);

    // 3. Import data from Excel file.
    try{
      allApplications = importApplicationsFromExcel( reapExcelDataFile, academicYear, applicationType );
    } catch (NoSuchSemesterException | IOException e ) {
      // TODO: Send warning message to frontend.
      System.out.println("Could not match semester name in one of the rows in the Excel file.");
    }

    // 4. Place students.
    combinedList = placeStudents( allApplications, departmentName );

    List<Application> mainApplications = combinedList.get(0);
    List<Application> waitingApplications = combinedList.get(1);

    for( int i = 0; i < mainApplications.size(); i++ ){
      mainApplications.get(i).setPlacementTable( mainList );
    }

    for( int i = 0; i < waitingApplications.size(); i++ ){
      waitingApplications.get(i).setWaitingBin( waitingBin );
    }

    System.out.println("\nAdding tasks...");

    // 5. Add task to the Dept. Erasmus Coordinator(s).
    for( DepartmentErasmusCoordinator coordinator: coordinators ){
      Task crdTask = new Task(
        "View the initial placements and confirm.",
        "01.01.2024");
      userClassService.addTaskToUserSid( coordinator.getSchoolId(), crdTask );
    }

    for (Application application : allApplications) {
      System.out.println( "Student ID: " + application.getStudent().getSchoolId());
      Task studentTask = new Task(
        "Accept / reject your placement.",
        "01.01.2024");
      userClassService.addTaskToUserSid( application.getStudent().getSchoolId(), studentTask );
    }



  }


}
