package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.Student;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface StudentRepository extends JpaRepository<Student, Long> {
        //    List<User> findBySchoolId(Long schoolId);
        //TODO   ???
}