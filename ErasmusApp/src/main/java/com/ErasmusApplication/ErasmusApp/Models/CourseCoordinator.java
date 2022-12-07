package com.ErasmusApplication.ErasmusApp.Models;

public class CourseCoordinator extends UserClass{
    // Properties
    private String faculty;
    private String department;
    private String course;

    public CourseCoordinator(String email, String firstName, String lastName, long schoolId) {
        super(email, firstName, lastName, schoolId);
    }

    // Constructors


}
