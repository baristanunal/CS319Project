package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.HostUniversityDepartment;
import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Repositories.HostUniversityDepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HostUniversityDepartmentService {

  HostUniversityDepartmentRepository hostUniDeptRepo;

  public List<HostUniversityDepartment> getHostUniDeptByName(String hostUniDeptName ){
    List<HostUniversityDepartment> hostUniDept = hostUniDeptRepo.findHostUniversityDepartmentsByDepartmentName(hostUniDeptName);
    if (hostUniDept.isEmpty()) {
      throw new IllegalStateException( "Host university with name: "
        + hostUniDeptName + " does not exist in the database." );
    }
    else{
      return hostUniDept;
    }
  }
}
