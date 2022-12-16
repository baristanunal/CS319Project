package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Exceptions.NoSuchSemesterException;
import com.ErasmusApplication.ErasmusApp.Models.*;
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
public class PlacementManagerService {

  // Properties
  StudentService studentService;
  HostUniversityDepartmentService hostUniversityDepartmentService;

  // Methods
  @PostMapping("/import")
  public List<Application> importApplicationsFromExcel(@RequestParam("file") MultipartFile reapExcelDataFile, int academicYear, String applicationType) throws NoSuchSemesterException, IOException {

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

    for( int i=1; i<worksheet.getPhysicalNumberOfRows(); i++ ) {

      Student student;
      String schoolId;
      double totalPoints;
      String hostUniversityName;
      String durationPreferred;
      String standardizedSemesterCode = null;
      HostUniversity hostUniversity;
      List<HostUniversity> preferredUniversities = new ArrayList<>();

      XSSFRow row = worksheet.getRow(i);

      // Get student id & create student instance.
      schoolId = row.getCell(4).getStringCellValue();
      student = studentService.getStudentBySchoolId(schoolId);

      // Get total points.
      totalPoints = row.getCell(11).getNumericCellValue();

      // Get preferred semester.
      durationPreferred = row.getCell(22).getStringCellValue();
      if( durationPreferred.equals("Bahar Dönemi")) {
        standardizedSemesterCode = academicYear + "/SPRING";
      }
      else if(durationPreferred.equals("Güz Dönemi") ) {
        standardizedSemesterCode = academicYear + "/FALL";
      }
      else {
        throw new NoSuchSemesterException("Could not match semester name in one of the rows in the Excel file.");
      }

      // Set preferred university #1.
      hostUniversityName = row.getCell(23).getStringCellValue();
      hostUniversity = new HostUniversity(hostUniversityName);
      preferredUniversities.add(hostUniversity);

      // Set preferred university #2.
      hostUniversityName = row.getCell(24).getStringCellValue();
      hostUniversity = new HostUniversity(hostUniversityName);
      preferredUniversities.add(hostUniversity);

      // Set preferred university #3.
      hostUniversityName = row.getCell(25).getStringCellValue();
      hostUniversity = new HostUniversity(hostUniversityName);
      preferredUniversities.add(hostUniversity);

      // Set preferred university #4.
      hostUniversityName = row.getCell(26).getStringCellValue();
      hostUniversity = new HostUniversity(hostUniversityName);
      preferredUniversities.add(hostUniversity);

      // Set preferred university #5.
      hostUniversityName = row.getCell(27).getStringCellValue();
      hostUniversity = new HostUniversity(hostUniversityName);
      preferredUniversities.add(hostUniversity);

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
      Integer curQuota = allUniversityDepartments.get(i).getQuota();
      quotas.put( curHostUniName, curQuota );
    }

    // 2. Place students.
    for( int i = 0; i < allApplications.size(); i++ ){

      List<HostUniversity> curPreferredUniversities = allApplications.get(i).getPreferredUniversities();

      for( int j = 0; j < curPreferredUniversities.size(); j++ ){

        Integer curQuota = quotas.get( curPreferredUniversities.get(i).getNameOfInstitution() );

        if( curQuota > 0 ){

          // 2.T.1 Update quota.
          curQuota--;
          quotas.put( curPreferredUniversities.get(i).getNameOfInstitution(), curQuota );

          // 2.T.2 Set placed university of the current application.
          allApplications.get(i).setPlacedHostUniversity( curPreferredUniversities.get(j) );

          // 2.T.3 Add application to the main list.
          mainList.add( allApplications.get(i) );
        }
        else{
          // 2.F Add application to the waiting bin.
          waitingBin.add( allApplications.get(i) );
        }
      }
    }

    // 3. Add main list and waiting bin to the combined list which will be returned.
    combinedList.add(mainList);
    combinedList.add(waitingBin);
    return combinedList;
  }


  public void getDataAndPlaceStudents( @RequestParam("file") MultipartFile reapExcelDataFile, int academicYear, String applicationType, String departmentName ){

    List<Application> allApplications = new ArrayList<>();
    List<List<Application>> combinedList = new ArrayList<>();

    try{
      allApplications = importApplicationsFromExcel( reapExcelDataFile, academicYear, applicationType );
    } catch (NoSuchSemesterException | IOException e ) {
      // TODO: Send warning message to frontend.
      System.out.println("Could not match semester name in one of the rows in the Excel file.");
    }

    combinedList = placeStudents( allApplications, departmentName );

  }

  // TODO: create erasmusManager
  // TODO: set isPlaced and isInWaitingBin



}
