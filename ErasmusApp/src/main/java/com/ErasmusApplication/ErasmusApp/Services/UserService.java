package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.UserClass;

import java.util.List;

public interface UserService {
    UserClass saveUser(UserClass user);
    String saveRole(String role);
    void addRoleToUser(String schoolId, String roleName);
    UserClass getUserBySchoolId(String schoolId);
    List<UserClass> getUsers();

}
