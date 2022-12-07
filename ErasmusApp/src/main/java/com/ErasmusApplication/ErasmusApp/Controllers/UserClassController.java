package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Services.UserClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserClassController {
    private final UserClassService userClassService;

    @Autowired
    public UserClassController(UserClassService userClassService) {
        this.userClassService = userClassService;
    }

    @GetMapping
    public List<UserClass> getUsers(){
        return userClassService.getUsers();
    }

    @PostMapping
    public void addNewUser(@RequestBody UserClass userClass) {
        userClassService.addNewUser(userClass);
    }
    @DeleteMapping(path = "{userClassId}")
    public void deleteUser(@PathVariable Long userClassId){
        userClassService.deleteStudent(userClassId);
    }
}
