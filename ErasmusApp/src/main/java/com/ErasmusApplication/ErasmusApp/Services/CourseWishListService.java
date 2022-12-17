package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.*;
import com.ErasmusApplication.ErasmusApp.Repositories.CourseWishListRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service @Transactional
@AllArgsConstructor
public class CourseWishListService {

    CourseWishListRepository courseWishListRepository;

    public CourseWishList saveCourseWishList(CourseWishList courseWishList, Application application){
        courseWishList.setApplication(application);
        return courseWishListRepository.save(courseWishList);
    }
    public CourseWishList getCourseWishList(Long wlId) {

        return courseWishListRepository.findById(wlId).orElseThrow(() -> new IllegalStateException(
                "CourseWishList With Id: " + wlId + " does not exist."
        ));
    }

    /**
     * Methods for Wishes
     * */
    //TASKS
    //TODO just use method in UserClassService
    public CourseWishList addWishToCourseWishList(Long wlId, Wish wish) {
        CourseWishList courseWishList= getCourseWishList(wlId);
        wish.setCourseWishList(courseWishList);
        boolean success = courseWishList.addWish(wish);
        //TODO
        if (!success) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Failed to add wish to CourseWishList with Id: " + wlId)
            );
        }
        return courseWishList;//TODO
    }
    public List<Wish> getAllWishes(Long wlId){
        CourseWishList courseWishList= getCourseWishList(wlId);
        List<Wish> wishes = courseWishList.getWishes();
        //TODO  return empty list
        if (wishes.isEmpty()){
            return null;
        }
        return wishes;
    }

    public boolean addWishes(Long wlId, List<Wish> wishes) {
        CourseWishList courseWishList= getCourseWishList(wlId);
        return courseWishList.addWishes(wishes);
    }

    public CourseWishList updateWish(Long wlId, Long wishId, Wish wish) {
        CourseWishList courseWishList= getCourseWishList(wlId);
        boolean isExist = courseWishList.updateWishByWishId(wishId,wish);

        if (!isExist){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Wish with Id: " + wish + " is not belong to CourseWishList with Id: " +wlId)
            );
        }

        return courseWishList;
    }
    public CourseWishList removeWishFromCourseWishList(Long wlId, Long wishId) {
        CourseWishList courseWishList= getCourseWishList(wlId);
        boolean success = courseWishList.removeWishFromCourseWishList(wishId);
        if (!success) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("CourseWishList with Id: " + wlId + " does not have wish with Id: " + wishId)
            );
        }
        return courseWishList;
    }




//
//
//    /**
//     * Methods for PreApproval
//     * */
//    public Application addPreApproval(Long appId, PreApproval preApproval){//Works
//        Application app = getApplication(appId);
//        app.setPreApproval(preApproval);
//        return app;
//    }
//    public Form getPreApproval(Long appId){ //Works
//        Application app = getApplication(appId);
//        return app.getPreApproval();
//    }
}
