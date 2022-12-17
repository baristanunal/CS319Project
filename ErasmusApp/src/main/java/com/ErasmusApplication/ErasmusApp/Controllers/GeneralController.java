package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.Models.*;
import com.ErasmusApplication.ErasmusApp.Security.JwtUtils;
import com.ErasmusApplication.ErasmusApp.Services.ApplicationService;
import com.ErasmusApplication.ErasmusApp.Services.StudentService;
import com.ErasmusApplication.ErasmusApp.Services.UserClassService;
import com.ErasmusApplication.ErasmusApp.TempClasses.RoleToUserForm;
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
    //TODO TODO
    //TODO TODO
    // TODO add role check for all methods

    // USERS
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UserClass> getUser(@PathVariable Long userId){

        return new ResponseEntity<>(userClassService.getUser(userId), HttpStatus.OK);
    }
    @GetMapping
    public List<UserClass> getUsers(){
        return userClassService.getUsers();
    }
    @PutMapping("/update/{userId}") // Try to delete this and combine this with just @RequestBody,  I tried but it gives the error : Content-Type '*/*;charset=UTF-8' is not supported
    public void updateStudent( @PathVariable("userId") Long userId, @RequestBody UserClass user){
        //TODO using token find its type
        String role = "";
        if (role.equals("student")){
            studentService.updateStudent(userId, (Student) user);
        }
        //TODO add for all users
    }


    // Methods for USER's TASKS
    @PostMapping("/{userId}/addTasks")
    public UserClass addTask(@PathVariable Long userId, @RequestBody Task taskToUpdate) {
        return userClassService.addTasks(userId,taskToUpdate);
    }
    @GetMapping("{userId}/getAllTasks")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long userId){
        return new ResponseEntity<>(studentService.getAllTasks(userId), HttpStatus.OK);
    }

    @PostMapping("{userId}/tasks/remove/{taskId}")
    public ResponseEntity<UserClass> removeTaskFromUser(@PathVariable Long userId, @PathVariable Long taskId){
        UserClass user = userClassService.removeTaskFromUser(userId, taskId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{userId}/tasks/update/{taskId}")
    public ResponseEntity<UserClass> updateTask(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody Task taskToUpdate){
        UserClass user = studentService.updateTask(userId, taskId, taskToUpdate);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // STUDENT's APPLICATION

    //TODO type of return
    @PostMapping("{userId}/application/cancel/{applicationId}")
    public Student cancelApplicationAfterPlaced(@PathVariable Long userId, @PathVariable Long applicationId){
        return studentService.cancelApplicationAfterPlaced(userId,applicationId);
    }
    @GetMapping("{userId}/application/getByType/{appTypeInt}")
    public Application getApplicationByApplicationType(@PathVariable Long userId, @PathVariable int appTypeInt) {
       String applicationType = "ERASMUS";
        if( appTypeInt == 1){
           applicationType = "EXCHANGE";
       }
        return studentService.getApplicationByApplicationType(userId,applicationType);
    }

    @GetMapping("{userId}/application/getById/{applicationId}")
    public Application getApplicationByApplicationId(@PathVariable Long userId,@PathVariable Long applicationId) {
        return studentService.getApplicationByApplicationId(userId,applicationId);
    }

    @PostMapping("{userId}/application/acceptApplicationRequest/{appTypeInt}")
    public Application acceptApplication(@PathVariable Long userId, @PathVariable int appTypeInt, @RequestBody HostUniversity nameOfUni){
        String applicationType = "ERASMUS";
        if( appTypeInt == 1){
            applicationType = "EXCHANGE";
        }
        return studentService.acceptApplicationRequest(userId,applicationType, nameOfUni.getNameOfInstitution());

    }

    //APPLICATION CLASS
    @GetMapping("{appId}/deneme")
    public CourseWishList createCourseWishList(@PathVariable Long appId){
        return applicationService.createCourseWishList(appId);
    }

}
