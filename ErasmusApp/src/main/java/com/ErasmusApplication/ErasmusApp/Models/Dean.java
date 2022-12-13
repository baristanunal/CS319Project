package com.ErasmusApplication.ErasmusApp.Models;

import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Dean extends UserClass{
    // Properties


    public Dean(String email, String firstName, String lastName, String schoolId, String faculty, String department,String password) {
        super(email, firstName, lastName, schoolId, faculty, department,password);
    }

    public Dean() {

    }
}
