package com.ErasmusApplication.ErasmusApp.Models;

public class DepartmentChair extends UserClass {
    // Properties
    private String faculty;
    private String department;

    public DepartmentChair(String email, String firstName, String lastName, long schoolId) {
        super(email, firstName, lastName, schoolId);
    }
}
