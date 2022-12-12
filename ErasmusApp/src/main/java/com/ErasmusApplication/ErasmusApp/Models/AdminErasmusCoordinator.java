package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class AdminErasmusCoordinator extends UserClass{
    // Properties
    private String faculty;

    public AdminErasmusCoordinator(String email, String firstName, String lastName, long schoolId, String faculty) {
        super(email, firstName, lastName, schoolId);
        this.faculty = faculty;
    }
    public AdminErasmusCoordinator() {

    }
}
