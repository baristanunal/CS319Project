package com.ErasmusApplication.ErasmusApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException{
    // Constructors
    public MyFileNotFoundException(String message) {
        super(message);
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
