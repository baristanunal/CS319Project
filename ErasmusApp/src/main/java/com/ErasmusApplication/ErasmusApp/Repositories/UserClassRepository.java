package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.DepartmentErasmusCoordinator;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserClassRepository extends JpaRepository<UserClass, Long> {

    @Query("select u from UserClass u where u.schoolId = ?1")
    Optional<UserClass> findBySchoolIdOpt(String schoolId);
    
    @Query("select u from UserClass u where u.schoolId = ?1")
    UserClass findBySchoolId(String schoolId);

//    List<DepartmentErasmusCoordinator> findByDepartmentAndRole(String department, String role);

//    UserClass findBySchoolId(String schoolId);
}
