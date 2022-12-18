package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.*;
import com.ErasmusApplication.ErasmusApp.Repositories.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service @Transactional @AllArgsConstructor
public class ApplicationService {
    public final ApplicationRepository applicationRepository;
    public final HostUniversityService hostUniversityService;
    public final CourseWishListService courseWishListService;

    /**
     * Methods for CRUD of Applications
     */
    public Application saveApplication(Student student, Application newApplication){
        newApplication.setStudent(student);
        return applicationRepository.save(newApplication);
    }

    public Application getApplication(Long applicationId){
        return applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                String.format(  "Application With Id: " + applicationId + " does not exist."
                                ))
                );
    }

    public Application getApplicationByStudentAndApplicationId(Long userId, Long applicationId){
        return applicationRepository.findByStudent_IdAndId(userId, applicationId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                String.format(  "Application With Id: " + applicationId + " does not belong to Student with Id: " + userId
                                ))
                );

    }

    public List<Application> getApplicationsByStudentId(Long userId){
        List list = applicationRepository.findByStudent_Id(userId);
        if(list == null || list.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "Student with With Id: " + userId + " does not have application"
                    ));
        }
        return list;
    }

    @Transactional
    public Application updateApplication(Long userId, Long applicationId,Application updatedApplication){

        Application application = getApplicationByStudentAndApplicationId(userId, applicationId);
        application.setAll(updatedApplication);
        return application;


    }
    public boolean removeApplicationOfStudent(Long applicationId, Long userId) {
        //This checks whether given applicationId correspond to application whose owner is user with userId
        Application application = getApplicationByStudentAndApplicationId(userId, applicationId);

        applicationRepository.deleteById(applicationId);
        return true;
    }

    public void removeApplication(Long userId, Long applicationId) {
        //This checks whether given applicationId correspond to application whose owner is user with userId
        Application application = getApplicationByStudentAndApplicationId(userId, applicationId);
        applicationRepository.deleteById(applicationId);
    }

    /**
     * Methods for PreferredUniversities
     * */

    public Application addPreferredUni(Long appId, String nameOfUni){
        Application app = getApplication(appId);
        app.addPreferredUniversity(nameOfUni);
        return app;
    }
    public List<String> getPreferredUniversities(Long appId){
        Application app = getApplication(appId);
        return app.getPreferredUniversities();
    }

    /**
     * Methods for PlacedHostUni
     * */

    public Application addPlacedUni(Long userId, Long appId, String nameOfUni){ // works
        Application app = getApplicationByStudentAndApplicationId(userId,appId);
        if( app.getPlacedHostUniversity() != null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "Application has aldread placed host uni")
            );
        }
        HostUniversity uni = hostUniversityService.getHostUniByName(nameOfUni);
        app.setPlacedHostUniversity(uni);
//        hostUniversityService.addPlacedApplication(nameOfUni, app);
        return app;
    }
    public HostUniversity getPlacedUni(Long appId){ // works
        Application app = getApplication(appId);
        if( app.getPlacedHostUniversity() == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "Application does not have placed uni yet.")
            );
        }
        return app.getPlacedHostUniversity();
    }


    /**
     * Methods for CourseWishList
     * */

    public CourseWishList createEmptyCourseWishList(Long appId){ // works
        Application app = getApplication(appId);
        if( app.getCourseWishlist() != null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "CourseWishList of application with id: " + appId + " has already placed host uni")
            );
        }
        CourseWishList courseWishList = new CourseWishList();
        return courseWishListService.saveCourseWishList(courseWishList,app);
    }

    public CourseWishList getCourseWishList(Long userId, String appType) {
        Long applicationId = getAppId(userId,appType);
        Application app = getApplicationByStudentAndApplicationId(userId,applicationId);
        CourseWishList wishList = app.getCourseWishlist();
        if(wishList == null){
             return createEmptyCourseWishList(applicationId);
        }
        return wishList;
    }

    public Long getAppId(Long userId, String appType) {
        Application application= applicationRepository.findByStudent_IdAndStudent_Applications_ApplicationType(userId, appType);
        return application.getId();
    }


    public CourseWishList getWlId(Long appId) {
        Application application= getApplication(appId);
        return application.getCourseWishlist();
    }
    public List<Wish> getAllWishesWithoutId(Long userId, String appType){
        Long applicationId = getAppId(userId,appType);
        CourseWishList courseWishList = getWlId(applicationId);
        List<Wish> wishes = courseWishList.getWishes();
        if (wishes.isEmpty()){
            return new ArrayList<Wish>();
        }
        return wishes;
    }
}
