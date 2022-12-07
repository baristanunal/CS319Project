package com.ErasmusApplication.ErasmusApp.Models;

public class FacultyAdminCommittee extends UserClass {
    public FacultyAdminCommittee(String email, String firstName, String lastName, long schoolId) {
        super(email, firstName, lastName, schoolId);
    }
    // Properties

    // Constructors

    // Methods
    public boolean approvePreApp(PreApproval preApproval){
        return true; // to be implemented
    }

    public boolean approveCourseTransfer(CourseTransfer courseTransfer){
        return true; // to be implemented
    }

}
