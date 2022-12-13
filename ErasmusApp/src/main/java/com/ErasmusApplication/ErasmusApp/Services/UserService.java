package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.Role;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;

import java.util.List;

public interface UserService {
    UserClass saveUser(UserClass user);
    Role saveRole(Role role);
    void addRoleToUser(String schoolId, String roleName);
    UserClass getUser(String schoolId);
    List<UserClass> getUsers();

}
