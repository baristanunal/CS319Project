package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.BilkentCourse;
import com.ErasmusApplication.ErasmusApp.Models.Course;
import com.ErasmusApplication.ErasmusApp.Repositories.BilkentCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class BilkentCourseService {
    private final BilkentCourseRepository bilkentCourseRepository;

    public BilkentCourse getCourse(Long courseId){
        return bilkentCourseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException(
                        "BilkentCourse with ID: " + courseId + " does not exist."
                ));
    }

    public BilkentCourse getCourseByCode(String courseCode) {
        return bilkentCourseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new IllegalStateException(
                        "BilkentCourse with code: " + courseCode + " does not exist."
                ));
    }
}
