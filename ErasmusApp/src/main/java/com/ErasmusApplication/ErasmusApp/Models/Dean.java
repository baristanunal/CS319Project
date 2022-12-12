package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Dean extends UserClass{
    // Properties
    private String faculty;


    public Dean(String email, String firstName, String lastName, long schoolId, String faculty) {
        super(email, firstName, lastName, schoolId);
        this.faculty = faculty;
    }

    public Dean(String faculty) {
        this.faculty = faculty;
    }

    public Dean() {

    }
}
