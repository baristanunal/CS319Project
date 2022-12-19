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

@Service
@Transactional
@AllArgsConstructor
public class ApplicationService {
    // Properties
    public final ApplicationRepository applicationRepository;
    public final HostUniversityService hostUniversityService;
    public final CourseWishListService courseWishListService;

    private final StudentService studentService;

    /**
     * Methods for CRUD of Applications
     */

    // Methods
    public Application saveApplication(Student student, Application newApplication){
        newApplication.setStudent(student);
        return applicationRepository.save(newApplication);
    }

    /** CONTRACT:
     PRE-CONDITIONS:
     * Application with the requested ID exists
     POST-CONDITIONS:
     * Corresponding Application is returned
     */
    public Application getApplication(Long applicationId){
        return applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                String.format(  "Application With Id: " + applicationId + " does not exist."
                                ))
                );
    }

    /** CONTRACT:
     PRE-CONDITIONS:
     * Application with the requested ID exists
     * User with the requested ID exists
     POST-CONDITIONS:
     * Corresponding Application is returned
     */
    public Application getApplicationByStudentAndApplicationId(Long userId, Long applicationId){
        return applicationRepository.findByStudent_IdAndId(userId, applicationId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                String.format(  "Application With Id: " + applicationId + " does not belong to Student with Id: " + userId
                                ))
                );

    }

    /** CONTRACT:
     PRE-CONDITIONS:
     * User with the requested ID exists
     POST-CONDITIONS:
     * Applications of the students are returned
     */
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

    /** CONTRACT:
     PRE-CONDITIONS:
     * User with the requested ID exists
     * Application with the requested ID exists
     * New updated application is provided
     POST-CONDITIONS:
     * Application is updated
     * Corresponding Application of the Corresponding student is returned
     */
    @Transactional
    public Application updateApplication(Long userId, Long applicationId,Application updatedApplication){

        Application application = getApplicationByStudentAndApplicationId(userId, applicationId);
        application.setAll(updatedApplication);
        return application;


    }

    /** CONTRACT:
     PRE-CONDITIONS:
     * User with the requested ID exists
     * Application with the requested ID exists
     POST-CONDITIONS:
     * Application with the id is deleted
     * Success of removal is returned
     */
    public boolean removeApplicationOfStudent(Long applicationId, Long userId) {
        //This checks whether given applicationId correspond to application whose owner is user with userId
        Application application = getApplicationByStudentAndApplicationId(userId, applicationId);

        applicationRepository.deleteById(applicationId);
        return true;
    }

    /** CONTRACT:
     PRE-CONDITIONS:
     * User with the requested ID exists
     * Application with the requested ID exists
     POST-CONDITIONS:
     * Application with the id is deleted
     */
    public void removeApplication(Long userId, Long applicationId) {
        //This checks whether given applicationId correspond to application whose owner is user with userId
        Application application = getApplicationByStudentAndApplicationId(userId, applicationId);
        applicationRepository.deleteById(applicationId);
    }

    /**
     * Methods for PreferredUniversities
     * */

    /** CONTRACT:
     PRE-CONDITIONS:
     * String which has the value of University name is passed as an argument
     * Application with the requested ID exists
    POST-CONDITIONS:
     * University is added to application
     */
    public Application addPreferredUni(Long appId, String nameOfUni){
        Application app = getApplication(appId);
        app.addPreferredUniversity(nameOfUni);
        return app;
    }

    /** CONTRACT:
     PRE-CONDITIONS:
     * Application with the requested ID exists
     POST-CONDITIONS:
     * All preferred universities in the application are returned
     */
    public List<String> getPreferredUniversities(Long appId){
        Application app = getApplication(appId);
        return app.getPreferredUniversities();
    }

    /**
     * Methods for PlacedHostUni
     * */

    /** CONTRACT:
     PRE-CONDITIONS:
     * String which has the value of University name is passed as an argument
     * Application with the requested ID exists
     * User with the requested ID exists
     POST-CONDITIONS:
     * University is added to application
     * Corresponding application is returned
     */
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
        //hostUniversityService.addPlacedApplication(nameOfUni, app);
        return app;
    }

    /** CONTRACT:
     PRE-CONDITIONS:
     * Application with the requested ID exists
     * Application has a valid placed HostUniversity
     POST-CONDITIONS:
     * Placed HostUniversity is returned
     */
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

    /** CONTRACT:
     PRE-CONDITIONS:
     * Application with the requested ID exists
     * Application has a valid CourseWishList
     POST-CONDITIONS:
     * CourseWishList is returned
     */
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

    /** CONTRACT:
     PRE-CONDITIONS:
     * Application with the requested ID exists
     * String which has the value of application type is passed as an argument
     * Application has a valid CourseWishList
     POST-CONDITIONS:
     * CourseWishList is returned
     */
    public CourseWishList getCourseWishList(Long userId, String appType) {
        Long applicationId = getAppId(userId,appType);
        Application app = getApplicationByStudentAndApplicationId(userId,applicationId);
        CourseWishList wishList = app.getCourseWishlist();
        if(wishList == null){
             return createEmptyCourseWishList(applicationId);
        }
        return wishList;
    }

    /** CONTRACT:
     PRE-CONDITIONS:
     * User with the requested ID exists
     * String which has the value of application type is passed as an argument
     POST-CONDITIONS:
     * ID of the application of the user is returned
     */
    public Long getAppId(Long userId, String appType) {

        Application application= studentService.getApplicationByApplicationType(userId,appType);
        return application.getId();
    }

    /** CONTRACT:
     PRE-CONDITIONS:
     * User with the requested ID exists
     POST-CONDITIONS:
     * CourseWishList of the user is returned
     */
    public CourseWishList getWlId(Long appId) {
        Application application= getApplication(appId);
        CourseWishList wishList = application.getCourseWishlist();

        if(wishList == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "Application does not have course wish list yet.")
            );
        }
        return wishList;
    }

    /** CONTRACT:
     PRE-CONDITIONS:
     * User with the requested ID exists
     * String which has the value of application type is passed as an argument
     POST-CONDITIONS:
     * List of the wishes of the user are returned
     */
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
