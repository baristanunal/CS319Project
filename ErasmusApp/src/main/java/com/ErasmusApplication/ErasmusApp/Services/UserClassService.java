package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.Role;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Repositories.RoleRepository;
import com.ErasmusApplication.ErasmusApp.Repositories.UserClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
@Service  @Transactional
public class UserClassService implements UserService{
//    ErasmusManager erasmusManager;
    private final RoleRepository roleRepository;
    private final UserClassRepository userClassRepository;

    @Autowired
    public UserClassService(RoleRepository roleRepository, UserClassRepository userClassRepository) {
        this.roleRepository = roleRepository;
        this.userClassRepository = userClassRepository;
    }

    @Override
    public UserClass saveUser(UserClass user) {
        Optional<UserClass> userBySchoolId = userClassRepository.findBySchoolIdOpt(user.getSchoolId());
        if( userBySchoolId.isPresent()){
            throw new IllegalStateException("School Id is taken!");
        }
        return userClassRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String schoolId, String roleName) {
        UserClass user = getUser(schoolId);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public UserClass getUser(String schoolId) {
        return userClassRepository.findBySchoolId(schoolId);
    }

    //    @Autowired
//    public UserClassService(ErasmusManager erasmusManager) {
//        this.erasmusManager = erasmusManager;
//    }
//TODO  add erasmus manager with its implementation
    public List<UserClass> getUsers(){
        return userClassRepository.findAll();
    }


    public void deleteStudent(Long userClassId) {
        boolean exist = userClassRepository.existsById(userClassId);
        if(!exist){
            throw new IllegalStateException("User with Id: " + userClassId + " does not exist!");
        }
        userClassRepository.deleteById(userClassId);
    }
}
