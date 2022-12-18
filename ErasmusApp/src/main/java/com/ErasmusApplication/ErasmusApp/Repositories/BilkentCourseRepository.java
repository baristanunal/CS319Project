package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.BilkentCourse;
import com.ErasmusApplication.ErasmusApp.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BilkentCourseRepository extends JpaRepository<BilkentCourse, Long> {
    @Query("select b from BilkentCourse b where b.courseCode = ?1")
    Optional<BilkentCourse> findByCourseCode(String courseCode);

}
