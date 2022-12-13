package com.ErasmusApplication.ErasmusApp.Models;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class AdminErasmusCoordinator extends UserClass{
    // Properties


    public AdminErasmusCoordinator(String email, String firstName, String lastName, String schoolId, String faculty, String department,String password) {
        super(email, firstName, lastName, schoolId, faculty, department,password);
    }

    public AdminErasmusCoordinator() {

    }
}
