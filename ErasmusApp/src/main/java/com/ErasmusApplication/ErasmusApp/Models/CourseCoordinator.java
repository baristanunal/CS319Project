package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class CourseCoordinator extends UserClass{
    // Properties
    private String faculty;
    private String department;
    private String course;

    // Constructors

    public CourseCoordinator(String email, String firstName, String lastName, long schoolId, String faculty, String department, String course) {
        super(email, firstName, lastName, schoolId);
        this.faculty = faculty;
        this.department = department;
        this.course = course;
    }

    public CourseCoordinator(String email, String firstName, String lastName, long schoolId, String faculty, String department) {
        super(email, firstName, lastName, schoolId);
        this.faculty = faculty;
        this.department = department;
    }

    public CourseCoordinator(String faculty, String department) {
        this.faculty = faculty;
        this.department = department;
    }

    public CourseCoordinator(String faculty, String department, String course) {
        this.faculty = faculty;
        this.department = department;
        this.course = course;
    }

    public CourseCoordinator() {

    }



}
