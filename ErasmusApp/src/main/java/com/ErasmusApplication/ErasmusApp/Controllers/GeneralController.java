package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.DataOnly.AddWishDao;
import com.ErasmusApplication.ErasmusApp.Models.*;
import com.ErasmusApplication.ErasmusApp.Security.AuthenticationRequest;
import com.ErasmusApplication.ErasmusApp.Security.JwtUtils;
import com.ErasmusApplication.ErasmusApp.Services.*;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/erasmus")
@AllArgsConstructor
public class GeneralController {
    private final UserClassService userClassService;
    private final StudentService studentService;
    private final JwtUtils jwtUtils;
    private final ApplicationService applicationService;
    private final CourseWishListService courseWishListService;
    private final DepartmentErasmusCoordinatorService departmentErasmusCoordinatorService;
    //TODO TODO
    //TODO TODO
    // TODO add role check for all methods

    // USERS
    @GetMapping("/getUser")
    public ResponseEntity<UserClass> getUser(@RequestHeader (name="Authorization") String token){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        return new ResponseEntity<>(userClassService.getUserBySchoolId(sId), HttpStatus.OK);
    }
    @GetMapping
    public List<UserClass> getUsers(){
        return userClassService.getUsers();
    }
    @PutMapping("/update") // Try to delete this and combine this with just @RequestBody,  I tried but it gives the error : Content-Type '*/*;charset=UTF-8' is not supported
    public void updateStudent( @RequestHeader (name="Authorization") String token, @RequestBody UserClass user){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();
        studentService.updateStudent(sId, (Student) user);

//
//        if (role.equals("student")){
//            studentService.updateStudent(userId, (Student) user);
//        }
        //TODO add for all users
    }


    // Methods for USER's TASKS
    @PostMapping("/addTask")
    public UserClass addTask(@RequestHeader (name="Authorization") String token, @RequestBody Task taskToUpdate) {
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        return userClassService.addTaskToUser(sId,taskToUpdate);
    }
    @GetMapping("/getAllTasks")
    public ResponseEntity<List<Task>> getAllTasks(@RequestHeader (name="Authorization") String token){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        return new ResponseEntity<>(studentService.getAllTasks(sId), HttpStatus.OK);
    }

    @PostMapping("/tasks/remove/{taskId}")
    public ResponseEntity<UserClass> removeTaskFromUser(@RequestHeader (name="Authorization") String token, @PathVariable Long taskId){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        UserClass user = userClassService.removeTaskFromUser(sId, taskId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/tasks/update/{taskId}")
    public ResponseEntity<UserClass> updateTask(@RequestHeader (name="Authorization") String token, @PathVariable Long taskId, @RequestBody Task taskToUpdate){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();
        UserClass user = studentService.updateTask(sId, taskId, taskToUpdate);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // STUDENT's APPLICATION

    //TODO type of return
    @PostMapping("/application/cancel/{applicationId}")
    public Student cancelApplicationAfterPlaced(@RequestHeader (name="Authorization") String token,  @PathVariable Long applicationId){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        return studentService.cancelApplicationAfterPlaced(sId,applicationId);
    }
    @GetMapping("/application/getByType/{appTypeInt}")
    public Application getApplicationByApplicationType(@RequestHeader (name="Authorization") String token,@PathVariable int appTypeInt) {
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();


       String applicationType = "ERASMUS";
        if( appTypeInt == 1){
           applicationType = "EXCHANGE";
       }
        return studentService.getApplicationByApplicationTypeSId(sId,applicationType);
    }

    @GetMapping("/application/getById/{applicationId}")
    public Application getApplicationByApplicationId(@RequestHeader (name="Authorization") String token,@PathVariable Long applicationId) {
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();


        return studentService.getApplicationByApplicationId(sId,applicationId);
    }

    @PostMapping("{userId}/application/acceptApplicationRequest/{appTypeInt}")
    public Application acceptApplication(@RequestHeader (name="Authorization") String token, @PathVariable int appTypeInt, @RequestBody String nameOfUni){

        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();


        String applicationType = "ERASMUS";
        if( appTypeInt == 1){
            applicationType = "EXCHANGE";
        }
        return studentService.acceptApplicationRequest(sId,applicationType, nameOfUni);

    }

    //APPLICATION CLASS
    @GetMapping("/application/createEmptyWishList/{applicationId}")
    public CourseWishList createCourseWishList(@RequestHeader (name="Authorization") String token, @PathVariable Long applicationId){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        return applicationService.createEmptyCourseWishList(applicationId);
    }

    @GetMapping("/application/getWishList/{appTypeInt}")
    public CourseWishList getCourseWishList(@RequestHeader (name="Authorization") String token, @PathVariable Long appTypeInt){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();


        String applicationType = "ERASMUS";
        if( appTypeInt == 1){
            applicationType = "EXCHANGE";
        }

        return applicationService.getCourseWishList(sId,applicationType);
    }

    //CourseWishList
    @PostMapping("/courseWishList/add/{wlId}")
    public CourseWishList addWishToCourseWishList(@RequestHeader (name="Authorization") String token, @PathVariable Long wlId, @RequestBody AddWishDao addWishDao) {
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        return courseWishListService.addWishToCourseWishList(sId, wlId,addWishDao);
    }
    @GetMapping("/courseWishList/getAllWishes/{wlId}")
    public List<Wish> getAllWishes(@RequestHeader (name="Authorization") String token,@PathVariable Long userId, @PathVariable Long wlId){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        return courseWishListService.getAllWishes(wlId);
    }

    @PostMapping("/courseWishList/{wlId}/removeWish/{wishId}")
    public CourseWishList removeWishFromUser(@RequestHeader (name="Authorization") String token,@PathVariable Long wlId, @PathVariable Long wishId){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        return courseWishListService.removeWishFromCourseWishList(wlId,wishId);
    }

    @PutMapping("/courseWishList/{wlId}/update/{wishId}")
    public CourseWishList updateWish(@RequestHeader (name="Authorization") String token,@PathVariable Long wlId, @PathVariable Long wishId, @RequestBody Wish wish){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        return courseWishListService.updateWish( wlId,wishId, wish);
    }

    @PostMapping("/courseWishList/addWishesToList/{wlId}")
    public boolean addWishesToCourseWishList(@RequestHeader (name="Authorization") String token,@PathVariable Long userId, @PathVariable Long wlId, @RequestBody List<Wish>  wishes) {
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        return courseWishListService.addWishes(wlId,wishes);
    }
    @GetMapping("/courseWishList/getPreApproval/{wlId}")
    public Form getPreApproval(@RequestHeader (name="Authorization") String token, @PathVariable Long wlId, @RequestBody List<Wish>  wishes) {
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        return courseWishListService.getPreApproval(wlId);
    }

    @GetMapping("/getPlacementManager")
    public PlacementManager getPlacementManager(@RequestHeader (name="Authorization") String token ){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        return departmentErasmusCoordinatorService.getPlacementManager(sId);

    }

    @GetMapping("/approvePreApproval/{appId}")
    public void approvePreApproval(@RequestHeader (name="Authorization") String token, @RequestBody Long appId ){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        departmentErasmusCoordinatorService.approvePreApproval(appId);

    }
    @GetMapping("/submitPreAppForApproval")
    public void approvePreApproval(@RequestHeader (name="Authorization") String token){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();

        courseWishListService.submitPreAppForApproval(sId);

    }
/*

//    @GetMapping("/{userId}}")
//    public Form a(){
//        long x = 1;
//        return courseWishListService.getPreApproval(x);
//    }
*/

}
