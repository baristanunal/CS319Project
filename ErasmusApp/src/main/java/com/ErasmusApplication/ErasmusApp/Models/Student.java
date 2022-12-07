package com.ErasmusApplication.ErasmusApp.Models;
import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
@Table(name = "Student")

public class Student extends UserClass {
    // properties
    private String department;
    private String academicYear;
    private String birthDate;
    private String nationality;
    private String gender;

//    @Id
//    private Long studentId;
    //private BilkentCourse[] passedCourses;
    //private CourseWishList courseWishList;
    // private Application application; // array olup olmayacağına karar vermeliyiz

}
