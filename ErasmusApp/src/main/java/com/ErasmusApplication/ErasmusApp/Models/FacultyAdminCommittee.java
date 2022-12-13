package com.ErasmusApplication.ErasmusApp.Models;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class FacultyAdminCommittee extends UserClass {

    public FacultyAdminCommittee() {

    }
    // Properties

    // Constructors

    // Methods


    public FacultyAdminCommittee(String email, String firstName, String lastName, String schoolId, String faculty, String department,String password) {
        super(email, firstName, lastName, schoolId, faculty, department,password);
    }


}
