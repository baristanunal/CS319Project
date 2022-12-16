package com.ErasmusApplication.ErasmusApp.Controllers;

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


    // Requests by user specific
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UserClass> getUser(@PathVariable Long userId){

        return new ResponseEntity<>(userClassService.getUser(userId), HttpStatus.OK);
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
    @PostMapping("{userId}/tasks/remove/{taskId}")
    public ResponseEntity<UserClass> removeTaskFromUser(@PathVariable Long userId, @PathVariable Long taskId){
        UserClass user = userClassService.removeTaskFromUser(userId, taskId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/{userId}/addTasks")
    public UserClass addTask(@PathVariable Long userId, @RequestBody Task taskToUpdate) {
        return userClassService.addTasks(userId,taskToUpdate);
    }

    // Methods for TASKS
    @GetMapping("{userId}/getAllTasks")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long userId){
        return new ResponseEntity<>(studentService.getAllTasks(userId), HttpStatus.OK);
    }


}
