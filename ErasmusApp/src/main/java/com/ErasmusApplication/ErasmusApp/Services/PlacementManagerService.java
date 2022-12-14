package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.HostUniversity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlacementManagerService {

  @PostMapping("/import")
  public List<Application> mapReapExcelDataToDB(@RequestParam("file") MultipartFile reapExcelDataFile, int academicYear) throws IOException {

    List<Application> applicationList = new ArrayList<Application>();
    XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
    XSSFSheet worksheet = workbook.getSheetAt(0);

    for(int i=1; i<worksheet.getPhysicalNumberOfRows(); i++) {
      Application tempApplication = new Application();
      String tempHostUniName;
      String durationPreferred;
      String standardizedSemesterCode = null;
      HostUniversity tempHostUni;
      List<HostUniversity> preferredUniversities = new ArrayList<>();

      XSSFRow row = worksheet.getRow(i);

      tempApplication.setTotalPoints( row.getCell(11).getNumericCellValue());

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
      tempApplication.setAppliedAcademicSemester(standardizedSemesterCode);

      tempHostUniName = row.getCell(23).getStringCellValue();
      tempHostUni = new HostUniversity(tempHostUniName);
      preferredUniversities.add(tempHostUni);
      tempHostUniName = row.getCell(24).getStringCellValue();
      tempHostUni = new HostUniversity(tempHostUniName);
      preferredUniversities.add(tempHostUni);
      tempHostUniName = row.getCell(25).getStringCellValue();
      tempHostUni = new HostUniversity(tempHostUniName);
      preferredUniversities.add(tempHostUni);
      tempHostUniName = row.getCell(26).getStringCellValue();
      tempHostUni = new HostUniversity(tempHostUniName);
      preferredUniversities.add(tempHostUni);
      tempHostUniName = row.getCell(27).getStringCellValue();
      tempHostUni = new HostUniversity(tempHostUniName);
      preferredUniversities.add(tempHostUni);

      tempApplication.setPreferredUniversities(preferredUniversities);

      applicationList.add(tempApplication);
    }
  }
}
