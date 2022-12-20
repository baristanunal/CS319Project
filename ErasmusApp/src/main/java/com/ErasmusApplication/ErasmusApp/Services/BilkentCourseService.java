package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.BilkentCourse;
import com.ErasmusApplication.ErasmusApp.Models.Course;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Repositories.BilkentCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor

public class BilkentCourseService {
    // Properties
    private final BilkentCourseRepository bilkentCourseRepository;

    // Methods

    public BilkentCourse saveBilkentCourse(BilkentCourse bilkentCourse) {
        return bilkentCourseRepository.save(bilkentCourse);
    }
    /** CONTRACT:
     PRE-CONDITIONS:
     * Course with the requested ID exists
     POST-CONDITIONS:
     * Corresponding Bilkent Course is returned
     */
    public BilkentCourse getCourse(Long courseId){
        return bilkentCourseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException(
                        "BilkentCourse with ID: " + courseId + " does not exist."
                ));
    }

    /** CONTRACT:
     PRE-CONDITIONS:
     * String is passed as an argument
     * Course with the requested String exists
     POST-CONDITIONS:
     * Corresponding Bilkent Course is returned
     */
    public BilkentCourse getCourseByCode(String courseCode) {
        return bilkentCourseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new IllegalStateException(
                        "BilkentCourse with code: " + courseCode + " does not exist."
                ));
    }
}
