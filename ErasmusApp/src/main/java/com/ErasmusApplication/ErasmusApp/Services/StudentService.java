package com.ErasmusApplication.ErasmusApp.Services;

import org.springframework.stereotype.Service;

@Service
public class StudentService extends UserService {

    //TODO  idk how this works
    public StudentService(ErasmusManager erasmusManager, LoginManager loginManager) {
        super(erasmusManager, loginManager);
    }

}
