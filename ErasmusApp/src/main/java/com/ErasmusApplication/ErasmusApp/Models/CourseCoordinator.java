package com.ErasmusApplication.ErasmusApp.Models;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class CourseCoordinator extends UserClass{
    // Properties
    private String faculty;
    private String course;

    // Constructors


    public CourseCoordinator() {

    }



}
