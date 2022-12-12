package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class FacultyAdminCommittee extends UserClass {
    public FacultyAdminCommittee(String email, String firstName, String lastName, long schoolId) {
        super(email, firstName, lastName, schoolId);
    }

    public FacultyAdminCommittee() {

    }
    // Properties

    // Constructors

    // Methods


}
