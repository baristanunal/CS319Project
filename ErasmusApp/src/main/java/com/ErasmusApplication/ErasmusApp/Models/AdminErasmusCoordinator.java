package com.ErasmusApplication.ErasmusApp.Models;

public class AdminErasmusCoordinator extends UserClass{
    // Properties
    private String faculty;

    public AdminErasmusCoordinator(String email, String firstName, String lastName, long schoolId) {
        super(email, firstName, lastName, schoolId);
    }
}
