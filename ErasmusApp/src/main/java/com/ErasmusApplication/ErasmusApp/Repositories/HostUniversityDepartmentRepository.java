package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.HostUniversityDepartment;
import com.ErasmusApplication.ErasmusApp.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HostUniversityDepartmentRepository extends JpaRepository<HostUniversityDepartment, Long> {

  @Query("select h from HostUniversityDepartment h where h.departmentName = ?1")
  List<HostUniversityDepartment> findHostUniversityDepartmentsByDepartmentName(String name);


}
