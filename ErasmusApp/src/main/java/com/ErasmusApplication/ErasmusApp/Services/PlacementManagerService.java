package com.ErasmusApplication.ErasmusApp.Services;

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
import java.util.List;

@Service
@Transactional
public class PlacementManagerService {

  // Properties
  StudentService studentService;

  // Methods
  @PostMapping("/import")
  public List<Application> importApplicationsFromExcel(@RequestParam("file") MultipartFile reapExcelDataFile, int academicYear, String applicationType) throws IOException {

    List<Application> applicationList = new ArrayList<Application>();
    XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
    XSSFSheet worksheet = workbook.getSheetAt(0);

    for( int i=1; i<worksheet.getPhysicalNumberOfRows(); i++ ) {
      // Application application = new Application();
      Student student = null;
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
        // throw new NoSuchSemesterException();
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

  public List<List<Application>> placeStudents( List<Application> allApplications ){

    List<Application> mainList = new ArrayList<>();
    List<Application> waitingBin = new ArrayList<>();
    List<List<Application>> combinedList = new ArrayList<>();



    combinedList.add(mainList);
    combinedList.add(waitingBin);
    return combinedList;
  }



}
