package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserClassRepository extends JpaRepository<UserClass, Long> {

    @Query("select u from UserClass u where u.schoolId = ?1")
    Optional<UserClass> findBySchoolId(long schoolId);


}
