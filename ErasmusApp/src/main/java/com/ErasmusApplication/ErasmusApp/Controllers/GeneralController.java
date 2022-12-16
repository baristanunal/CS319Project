package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Models.Task;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Security.JwtUtils;
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
        if (role.equals("ROLE_STUDENT")){
            studentService.updateStudent(userId, (Student) user);
        }
        //TODO add for all users
    }


    // Methods for USER's TASKS
    @PostMapping("/{userId}/addTasks")
    public UserClass addTasks(@PathVariable Long userId, @RequestBody Task taskToUpdate) {
        return userClassService.addTasks(userId,taskToUpdate);
    }
    @PostMapping("/{userId}/addTask")
    public UserClass addTask(@PathVariable Long userId, @RequestBody Task taskToUpdate) {
        return userClassService.addTaskToUser(userId,taskToUpdate);
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
    public ResponseEntity<Student> updateTask(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody Task taskToUpdate){
        Student student = studentService.updateTask(userId, taskId, taskToUpdate);

        return new ResponseEntity<>(student, HttpStatus.OK);
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

    //APPLICATION CLASS
    @PostMapping("{userId}/application/acceptApplicationRequest/{appTypeInt}")
    public Boolean acceptApplication(@PathVariable Long userId, @PathVariable int appTypeInt, @RequestBody String nameOfUni){
        String applicationType = "ERASMUS";
        if( appTypeInt == 1){
            applicationType = "EXCHANGE";
        }
        studentService.acceptApplicationRequest(userId,applicationType, nameOfUni);
        return true;
    }

}
