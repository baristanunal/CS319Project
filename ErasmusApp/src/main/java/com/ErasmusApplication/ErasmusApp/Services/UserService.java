package com.ErasmusApplication.ErasmusApp.Services;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    ErasmusManager erasmusManager;

    @Autowired
    public UserService(ErasmusManager erasmusManager) {

        this.erasmusManager = erasmusManager;
    }
}
