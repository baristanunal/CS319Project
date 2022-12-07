package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserClassRepository extends JpaRepository<UserClass, Long> {
    //    List<User> findBySchoolId(Long schoolId);

}
