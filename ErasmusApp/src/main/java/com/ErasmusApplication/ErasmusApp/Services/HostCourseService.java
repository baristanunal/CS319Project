package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import com.ErasmusApplication.ErasmusApp.Models.CourseWishList;
import com.ErasmusApplication.ErasmusApp.Models.HostCourse;
import com.ErasmusApplication.ErasmusApp.Models.HostUniversity;
import com.ErasmusApplication.ErasmusApp.Repositories.HostCourseRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.Host;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class HostCourseService {
    private final HostCourseRepository hostCourseRepository;


    public HostCourse saveHostCourse(HostCourse  hostCourse, HostUniversity hostUniversity){
        hostCourse.setHostUniversity(hostUniversity);
        return hostCourseRepository.save(hostCourse);
    }
    public HostCourse getHostCourseByName(String courseName) {

        return hostCourseRepository.findByNameOfCourse(courseName).orElseThrow(() -> new IllegalStateException(
                "HostCourse With name: " + courseName + " does not exist."
        ));
    }
    public HostCourse createIfNotExistOrReturn(HostCourse hostCourse, HostUniversity hostUniversity) {
        Boolean exist = hostCourseRepository.existsByHostUniversity_NameOfInstitutionAndNameOfCourse(hostUniversity.getNameOfInstitution(),hostCourse.getNameOfCourse());

        if (exist){
            return getHostCourseByName(hostCourse.getNameOfCourse());
        }
        return saveHostCourse(hostCourse,hostUniversity);
    }
}
