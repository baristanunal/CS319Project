package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.*;
import com.ErasmusApplication.ErasmusApp.Repositories.DepartmentErasmusCoordinatorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentErasmusCoordinatorService {

    DepartmentErasmusCoordinatorRepository departmentErasmusCoordinatorRepository;
    ApplicationService applicationService;
    public DepartmentErasmusCoordinator saveDepartmentErasmusCoordinator(DepartmentErasmusCoordinator user) {
        DepartmentErasmusCoordinator depErCoord = departmentErasmusCoordinatorRepository.findBySchoolId(user.getSchoolId());
        System.out.println( "Dept. Erasmus Coordinator Id: " + user.getSchoolId());
        if( depErCoord != null){
            throw new IllegalStateException("School Id is taken!");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return departmentErasmusCoordinatorRepository.save(user);
    }

    public DepartmentErasmusCoordinator getDepartmentErasmusCoordinator(Long userId) {

        return departmentErasmusCoordinatorRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "User With Id: " + userId + " does not exist."
        ));
    }


    public DepartmentErasmusCoordinator getBySchoolId(String schoolId) {
        DepartmentErasmusCoordinator coord = departmentErasmusCoordinatorRepository.findBySchoolId(schoolId);
        if (coord == null){
            System.out.println(schoolId);
            throw new IllegalStateException("depcoord does not exists");

        }
        return coord;
    }
    public PlacementManager getPlacementManager(String schoolId){
        DepartmentErasmusCoordinator coord = getBySchoolId(schoolId);
        return coord.getPlacementManager();
    }

    public void approvePreApproval(Long applicationId){
        Application application = applicationService.getApplication(applicationId);
        CourseWishList courseWishList = application.getCourseWishlist();
        courseWishList.setApproved(true);
    }
}
