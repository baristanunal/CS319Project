package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double ECTS_credit;
    private String nameOfCourse;
    private String courseCode;


    public Course(Double ECTS_credit, String nameOfCourse, String courseCode) {
        this.ECTS_credit = ECTS_credit;
        this.nameOfCourse = nameOfCourse;
        this.courseCode = courseCode;
    }

    public Course() {
        this.getId();
    }


}
