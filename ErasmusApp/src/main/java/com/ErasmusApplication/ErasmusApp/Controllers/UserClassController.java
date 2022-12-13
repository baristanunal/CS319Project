package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.Models.Role;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Services.UserClassService;
import com.ErasmusApplication.ErasmusApp.TempClasses.RoleToUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
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
