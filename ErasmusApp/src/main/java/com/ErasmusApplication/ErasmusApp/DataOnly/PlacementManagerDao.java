package com.ErasmusApplication.ErasmusApp.DataOnly;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class PlacementManagerDao {

  String academicYear;
  String applicationType;
  String departmentName;

}
