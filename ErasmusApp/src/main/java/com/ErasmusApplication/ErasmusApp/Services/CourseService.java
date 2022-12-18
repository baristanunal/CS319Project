package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.*;
import com.ErasmusApplication.ErasmusApp.Repositories.CourseRepository;
import com.ErasmusApplication.ErasmusApp.Repositories.HostCourseRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.Host;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;



    public Course getCourse(Long courseId){
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException(
                        "Course with ID: " + courseId + " does not exist."
                ));
    }

    public Course getCourseByCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Course with code: " + courseCode + " does not exist."
                ));
    }
}
