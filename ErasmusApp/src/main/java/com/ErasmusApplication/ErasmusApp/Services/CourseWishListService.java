package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.DataOnly.AddWishDao;
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

    private final CourseWishListRepository courseWishListRepository;
    private final PreApprovalService preApprovalService;
    private final HostCourseService hostCourseService;
    private final HostUniversityService hostUniversityService;
    private final CourseService courseService;
    private final BilkentCourseService bilkentCourseService;
    public CourseWishList saveCourseWishList(CourseWishList courseWishList, Application application){
        courseWishList.setApplication(application);
        application.setCourseWishlist(courseWishList);
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
    public CourseWishList addWishToCourseWishList(String sId, Long wlId, AddWishDao addWishDao) {
        CourseWishList courseWishList = getCourseWishList(wlId);
        Application application = courseWishList.getApplication();
        if(application == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("CourseWishList with Id: " + wlId + " does not have application which should be there. Ask for help.")
            );
        }
        String nameOfHostUni = application.getNameOfPlacedHostUniversity();
        HostUniversity hostUniversity = hostUniversityService.getHostUniByName(nameOfHostUni);
        HostCourse tempHostCourse = new HostCourse(addWishDao.getHostEcts_credit(),addWishDao.getHostCourseName(),addWishDao.getHostCourseCode());
        HostCourse hostCourse = hostCourseService.createIfNotExistOrReturn(tempHostCourse,hostUniversity);
        Wish wish = new Wish(addWishDao.getIntent(),addWishDao.getStanding(),addWishDao.getSyllabus());
        BilkentCourse tempBilkentCourse = new BilkentCourse(addWishDao.getBilkentEcts_credit(),addWishDao.getBilkentCourseName(),addWishDao.getBilkentCourseCode(),addWishDao.getBilkentCourseType());
        BilkentCourse bilkentCourse = bilkentCourseService.getCourseByCode(tempBilkentCourse.getCourseCode());
        wish.setBilkentCourse(bilkentCourse);
        wish.setCourseToCountAsBilkentCourse(hostCourse);
        wish.setCourseWishList(courseWishList);
        courseWishList.addWish(wish);
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
    /**
     * Methods for PreApproval
     * */
    public PreApproval addEmptyPreApproval(Long wlId){//Works
        CourseWishList courseWishList = getCourseWishList(wlId);

        PreApproval preApproval = new PreApproval();

        return preApprovalService.savePreApprovalToCourseWishList(preApproval,courseWishList);
    }
    public Form getPreApproval(Long wlId){ //Works
        CourseWishList courseWishList = getCourseWishList(wlId);
//        PreApproval preApproval = courseWishList.getPreApproval();
//        if(preApproval == null){
//            return addEmptyPreApproval(wlId);
//        }
//        return preApproval;
        return new Form();
    }
}
