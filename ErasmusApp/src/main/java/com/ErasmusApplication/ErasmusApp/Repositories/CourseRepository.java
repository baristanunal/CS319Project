package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.Course;
import com.ErasmusApplication.ErasmusApp.Models.HostCourse;
import com.ErasmusApplication.ErasmusApp.Models.HostUniversity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseCode(String courseCode);



}
