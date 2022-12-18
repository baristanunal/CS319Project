package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Exceptions.NoSuchSemesterException;
import com.ErasmusApplication.ErasmusApp.Models.*;
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
  private final WaitingBinRepository waitingBinRepository;

  // Methods
  public List<Application> importApplicationsFromExcel( MultipartFile reapExcelDataFile, String academicYear, String applicationType) throws NoSuchSemesterException, IOException {

    /*
    List<Application> applicationList = new ArrayList<Application>();
    XSSFWorkbook workbook = null;
    XSSFSheet worksheet;
    try{
      workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
    } catch (IOException e ) {
      // TODO: Send warning to frontend.
      System.out.println("Could not read Excel workbook");
    }
    */

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

      List<String> curPreferredUniversities = allApplications.get(i).getPreferredUniversities();

      for( int j = 0; j < curPreferredUniversities.size(); j++ ){

        int curQuota = quotas.get( curPreferredUniversities.get(j) );

        if( curQuota > 0 ){

          // 2.T.1 Update quota.
          curQuota--;
          quotas.put( curPreferredUniversities.get(j), curQuota );

          // 2.T.2 Set placed university of the current application.
          hostUniversityService.connectApplicationHostUniversity(curPreferredUniversities.get(j), allApplications.get(i));

          // 2.T.3 Add application to the main list.
          mainList.add( allApplications.get(i) );
          allApplications.get(i).setPlaced( true );
          allApplications.get(i).setInWaitingBin( false );
        }
        else{
          // 2.F Add application to the waiting bin.
          waitingBin.add( allApplications.get(i) );
          allApplications.get(i).setPlaced( false );
          allApplications.get(i).setInWaitingBin( true );
        }
      }
    }

    // 3. Add main list and waiting bin to the combined list which will be returned.
    combinedList.add(mainList);
    combinedList.add(waitingBin);
    return combinedList;
  }


  public void getDataAndPlaceStudents( MultipartFile reapExcelDataFile, String academicYear, String applicationType, String departmentName ){

    List<Application> allApplications = new ArrayList<>();
    List<List<Application>> combinedList;
    List<DepartmentErasmusCoordinator> coordinators;

    // 1. Get all coordinators of the department.
    coordinators = userClassService.getCoordinatorsByDepartment( departmentName );
    PlacementManager placementManager = new PlacementManager( coordinators );

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
      if(allApplications == null){
        System.out.println("allApplications is NULL.");
      }
      else {
        System.out.println("allApplications is not NULL.");
      }
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

    System.out.println("Finished getDataAndPlaceStudents.");

  }





}
