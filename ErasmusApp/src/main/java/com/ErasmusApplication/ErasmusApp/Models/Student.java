package com.ErasmusApplication.ErasmusApp.Models;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Table(name = "Student")
@Data
public class Student extends UserClass {
    // properties
    private String department;
    private String academicYear;
    private String birthDate;
    private String nationality;
    private String gender;

//    public Student(String email, String firstName, String lastName, long schoolId) {
//        super(email, firstName, lastName, schoolId);
//    }

    public Student(String email, String firstName, String lastName, long schoolId, String department, String academicYear, String birthDate, String nationality, String gender) {
        super(email, firstName, lastName, schoolId);
        this.department = department;
        this.academicYear = academicYear;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.gender = gender;
    }

    public Student(String department, String academicYear, String birthDate, String nationality, String gender) {
        this.department = department;
        this.academicYear = academicYear;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.gender = gender;
    }

    public Student() {

    }


    //    @Id
//    private Long studentId;
    // @Id
    // private Long studentId;
    //private BilkentCourse[] passedCourses;
    //private CourseWishList courseWishList;
    // private Application application; // array olup olmayacağına karar vermeliyiz

}
