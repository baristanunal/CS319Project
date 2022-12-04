package com.ErasmusApplication.ErasmusApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
@Service
public class LoginManager {
    Authenticator auth;
    //TODO
    @Autowired
    public LoginManager(Authenticator auth) {
        this.auth = auth;
    }
}
