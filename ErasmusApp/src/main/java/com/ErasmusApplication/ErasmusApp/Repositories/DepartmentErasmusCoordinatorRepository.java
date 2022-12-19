package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import com.ErasmusApplication.ErasmusApp.Models.DepartmentErasmusCoordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public interface DepartmentErasmusCoordinatorRepository extends JpaRepository<DepartmentErasmusCoordinator, Long> {
    @Query("select d from DepartmentErasmusCoordinator d where d.schoolId = ?1")
    DepartmentErasmusCoordinator findBySchoolId(String schoolId);


}
