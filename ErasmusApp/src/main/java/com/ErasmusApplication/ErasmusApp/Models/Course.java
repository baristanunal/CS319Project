package com.ErasmusApplication.ErasmusApp.Models;

import javax.persistence.*;
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

    private String courseType;

    public Course(Double ECTS_credit, String nameOfCourse, String courseCode,String courseType) {
        this.ECTS_credit = ECTS_credit;
        this.nameOfCourse = nameOfCourse;
        this.courseCode = courseCode;
        this.courseType = courseType;
    }

    public Course() {

    }


}
