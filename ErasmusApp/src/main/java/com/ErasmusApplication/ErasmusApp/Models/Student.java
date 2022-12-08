package com.ErasmusApplication.ErasmusApp.Models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Iterator;
import java.util.List;

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
    @JsonManagedReference
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Application> applications;

//    public Student(String email, String firstName, String lastName, long schoolId) {
//        super(email, firstName, lastName, schoolId);
//    }


    public Student(String email, String firstName, String lastName, long schoolId, String department, String academicYear, String birthDate, String nationality, String gender, List<Application> applications) {
        super(email, firstName, lastName, schoolId);
        this.department = department;
        this.academicYear = academicYear;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.gender = gender;
        this.applications = applications;
    }

    public Student(String department, String academicYear, String birthDate, String nationality, String gender, List<Application> applications) {
        this.department = department;
        this.academicYear = academicYear;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.gender = gender;
        this.applications = applications;
    }

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


    public Application getApplicationByType(String applicationType){
        Application application;
        Iterator<Application> iterator = applications.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getApplicationType() == applicationType){
                return iterator.next();
            }
        }
        return null;
    }
    public void removeApplication(Application application) {
        applications.remove(application);
    }
    public void addApplication(Application application){
        applications.add(application);
    }
    public void removeApplicationByApplicationType(String applicationType) {
        Iterator<Application> iterator = applications.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getApplicationType() == applicationType){
                iterator.remove();
            }
        }

    }


    //    @Id
//    private Long studentId;
    // @Id
    // private Long studentId;
    //private BilkentCourse[] passedCourses;
    //private CourseWishList courseWishList;
    // private Application application; // array olup olmayacağına karar vermeliyiz

}
