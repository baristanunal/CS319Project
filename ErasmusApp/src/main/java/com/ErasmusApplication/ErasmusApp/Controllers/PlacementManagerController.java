package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.DataOnly.PlacementManagerDao;
import com.ErasmusApplication.ErasmusApp.Models.PlacementManager;
import com.ErasmusApplication.ErasmusApp.Security.AuthenticationRequest;
import com.ErasmusApplication.ErasmusApp.Services.PlacementManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class PlacementManagerController {

  PlacementManagerService placementManagerService = new PlacementManagerService();

  @PostMapping("/getDataFromExcel")
  public void getDataAndPlaceStudents(@RequestParam("file") MultipartFile reapExcelDataFile, @RequestBody PlacementManagerDao data ){
    placementManagerService.getDataAndPlaceStudents( reapExcelDataFile, data.getAcademicYear(), data.getApplicationType(), data.getDepartmentName() );
  }

}
