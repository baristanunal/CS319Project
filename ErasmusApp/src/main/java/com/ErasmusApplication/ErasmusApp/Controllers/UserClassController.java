package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.Models.Role;
import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Security.JwtUtils;
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



    @GetMapping
    public List<UserClass> getUsers(@RequestHeader (name="Authorization") String token){
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);


        System.out.println(a);
        return userClassService.getUsers();
    }

    @PostMapping("/save")
    public UserClass saveUser(@RequestBody UserClass userClass) {

        return userClassService.saveUser(userClass);
    }
    @PostMapping("/role/save")
    public Role saveRole(@RequestBody Role role) {

        return userClassService.saveRole(role);
    }

    @PostMapping("/role/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleToUserForm form) {
        userClassService.addRoleToUser(form.getUserName(),form.getRoleName());
    }



    @DeleteMapping(path = "{userClassId}")
    public void deleteUser(@PathVariable Long userClassId){
        userClassService.deleteStudent(userClassId);
    }
}
