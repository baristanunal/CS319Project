package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class DepartmentChair extends UserClass {
    // Properties
    private String faculty;
    private String department;

    public DepartmentChair(String email, String firstName, String lastName, long schoolId, String faculty, String department) {
        super(email, firstName, lastName, schoolId);
        this.faculty = faculty;
        this.department = department;
    }

    public DepartmentChair(String faculty, String department) {
        this.faculty = faculty;
        this.department = department;
    }

    public DepartmentChair() {

    }
}
