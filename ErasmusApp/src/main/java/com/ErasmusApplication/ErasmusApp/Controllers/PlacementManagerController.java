package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.DataOnly.PlacementManagerDao;
import com.ErasmusApplication.ErasmusApp.Models.PlacementManager;
import com.ErasmusApplication.ErasmusApp.Security.AuthenticationRequest;
import com.ErasmusApplication.ErasmusApp.Services.PlacementManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@Component
public class PlacementManagerController {

  PlacementManagerService placementManagerService;


  @PostMapping("/getDataFromExcel")
  public void getDataAndPlaceStudents(@RequestPart("file") MultipartFile reapExcelDataFile, @RequestPart("PlacementManagerDao") PlacementManagerDao data ){
    placementManagerService.getDataAndPlaceStudents( reapExcelDataFile, data.getAcademicYear(), data.getApplicationType(), data.getDepartmentName() );
    System.out.println("Get data from Excel.");
  }

  /*
  @PostMapping("/getDataFromExcel")
  public void getDataAndPlaceStudents( @RequestBody String departmentName){
    //placementManagerService.getDataAndPlaceStudents( reapExcelDataFile, data.getAcademicYear(), data.getApplicationType(), data.getDepartmentName() );
    System.out.println("Get data from Excel.");
  }
  */


  @PostMapping("/testExcel")
  public void testFunc(@RequestParam("file") MultipartFile reapExcelDataFile ){
    System.out.println("Test.");
  }

}
