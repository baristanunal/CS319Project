package com.ErasmusApplication.ErasmusApp.Models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Application> applications;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private WaitingBin waitingBin;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private PlacementTable placementTable;

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


    //TODO unnecessary, if we want to delete or remove, we will iterate over List and we could handle it if it does not exist
    public boolean checkExistenceOfApplication(Long applicationId){
        Iterator<Application> iterator = applications.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId() == applicationId){
                return true;
            }
        }
        return false;
    }
    public Application getApplicationByType(String applicationType){
        Iterator<Application> iterator = applications.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getApplicationType() == applicationType){
                return iterator.next();
            }
        }
        return null;
    }
    public void addApplication(Application application){
        applications.add(application);
    }

    public boolean removeApplication(Application application) {
        return applications.remove(application);
    }
    public boolean removeApplicationById(Long applicationId) {
        Iterator<Application> iterator = applications.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId() == applicationId){
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    public boolean removeApplicationByApplicationType(String applicationType) {
        Iterator<Application> iterator = applications.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getApplicationType() == applicationType){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    //TODO check whether this works or not
    public boolean updateApplicationByApplicationType(String applicationType, Application application) {
        for (int i = 0; i < applications.size(); i++){
            if(applications.get(i).getApplicationType() == applicationType){
                applications.get(i).setAll(application);
                return true;
            }
        }
        return false;
    }

    public boolean updateApplicationByApplicationId(Long applicationId, Application application) {
        for (int i = 0; i < applications.size(); i++){
            if(applications.get(i).getId() == applicationId){
                applications.get(i).setAll(application);
                return true;
            }
        }
        return false;
    }
    //    @Id
//    private Long studentId;
    // @Id
    // private Long studentId;
    //private BilkentCourse[] passedCourses;
    //private CourseWishList courseWishList;
    // private Application application; // array olup olmayacağına karar vermeliyiz

}
