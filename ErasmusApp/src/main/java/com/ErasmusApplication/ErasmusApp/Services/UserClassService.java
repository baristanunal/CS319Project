package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Repositories.StudentRepository;
import com.ErasmusApplication.ErasmusApp.Repositories.UserClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserClassService {
//    ErasmusManager erasmusManager;
    private final UserClassRepository userClassRepository;

    @Autowired
    public UserClassService(UserClassRepository userClassRepository) {
        this.userClassRepository = userClassRepository;
    }

    //    @Autowired
//    public UserClassService(ErasmusManager erasmusManager) {
//        this.erasmusManager = erasmusManager;
//    }
//TODO  add erasmus manager with its implementation
    public List<UserClass> getUsers(){
        return userClassRepository.findAll();
    }

    public void addNewUser(UserClass user) {
        Optional<UserClass> userBySchoolId = userClassRepository.findBySchoolId(user.getSchoolId());
        if( userBySchoolId.isPresent()){
            throw new IllegalStateException("School Id is taken!");
        }
        userClassRepository.save(user);
    }

    public void deleteStudent(Long userClassId) {
        boolean exist = userClassRepository.existsById(userClassId);
        if(!exist){
            throw new IllegalStateException("User with Id: " + userClassId + " does not exist!");
        }
        userClassRepository.deleteById(userClassId);
    }
}
