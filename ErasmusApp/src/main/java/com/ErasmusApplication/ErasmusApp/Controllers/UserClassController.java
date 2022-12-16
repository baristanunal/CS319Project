package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.Models.*;
import com.ErasmusApplication.ErasmusApp.Security.JwtUtils;
import com.ErasmusApplication.ErasmusApp.Services.ApplicationService;
import com.ErasmusApplication.ErasmusApp.Services.StudentService;
import com.ErasmusApplication.ErasmusApp.Services.TaskService;
import com.ErasmusApplication.ErasmusApp.Services.UserClassService;
import com.ErasmusApplication.ErasmusApp.TempClasses.RoleToUserForm;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserClassController {
    private final UserClassService userClassService;
    private final JwtUtils jwtUtils;




    //TODO sil
    private final StudentService studentService;
    private final ApplicationService applicationService;

    @PostMapping("/deneme/{id1}/{id2}")
    public ResponseEntity<Application> a(@PathVariable Long id1, @PathVariable Long id2){
        try {
            Application a = applicationService.addPlacedUni(id1, "hostUni1");
            return new ResponseEntity<>(a,HttpStatus.OK);

        }catch (IllegalStateException e){
            System.out.println("BBB");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/deneme2/{id1}")
    public ResponseEntity<HostUniversity> a2(@PathVariable Long id1){
        HostUniversity a = applicationService.getPlacedUni(id1);
        return new ResponseEntity<>(a,HttpStatus.OK);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UserClass> getUser( @PathVariable Long userId){

        return new ResponseEntity<>(userClassService.getUser(userId), HttpStatus.OK);
    }
    @GetMapping
    public List<UserClass> getUsers(@RequestHeader (name="Authorization") String token){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);

        return userClassService.getUsers();
    }

    @PostMapping("{userId}/tasks/remove/{taskId}")
    public ResponseEntity<UserClass> removeTaskFromUser(@PathVariable Long userId, @PathVariable Long taskId){
        UserClass user = userClassService.removeTaskFromUser(userId, taskId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/save")
    public UserClass saveUser(@RequestBody UserClass userClass) {

        return userClassService.saveUser(userClass);
    }
    @PostMapping("/{userId}/addTasks")
    public UserClass addTasks(@PathVariable Long userId, @RequestBody Task taskToUpdate) {
        return userClassService.addTasks(userId,taskToUpdate);
    }

    @PostMapping("/role/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleToUserForm form) {
        userClassService.addRoleToUser(form.getUserName(),form.getRoleName());
    }



    @DeleteMapping(path = "{userClassId}")
    public void deleteUser(@PathVariable Long userClassId){
        userClassService.deleteUser(userClassId);
    }
}
