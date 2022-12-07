package com.ErasmusApplication.ErasmusApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    ErasmusManager erasmusManager;
    //LoginManager loginManager;
    //TODO  idk how this works

    @Autowired
    public StudentService(ErasmusManager erasmusManager) {
        this.erasmusManager = erasmusManager;
    }
}
