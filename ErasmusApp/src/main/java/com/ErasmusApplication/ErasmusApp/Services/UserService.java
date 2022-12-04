package com.ErasmusApplication.ErasmusApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class UserService {
    ErasmusManager erasmusManager;
    LoginManager loginManager;

    @Autowired
    public UserService(ErasmusManager erasmusManager, LoginManager loginManager) {
        this.erasmusManager = erasmusManager;
        this.loginManager = loginManager;
    }
}
