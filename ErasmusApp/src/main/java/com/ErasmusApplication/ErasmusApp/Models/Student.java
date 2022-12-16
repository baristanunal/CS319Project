package com.ErasmusApplication.ErasmusApp.Models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.Data;

import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "Student")
@Data
public class Student extends UserClass {
    // properties
    @JsonIgnore
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Application> applications;

    private String academicYear;
    private String birthDate;
    private String nationality;
    private String gender;

    public Student() {
    }

    public Student(String email, String firstName, String lastName, String schoolId, String faculty, String department, String academicYear, String birthDate, String nationality, String gender, String password) {
        super(email, firstName, lastName, schoolId, faculty, department,password);
        this.academicYear = academicYear;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.gender = gender;
    }

    public Student(String email, String firstName, String lastName, String schoolId, String faculty, String department,String password) {
        super(email, firstName, lastName, schoolId, faculty, department,password);
    }



    //TODO unnecessary, if we want to delete or remove, we will iterate over List and we could handle it if it does not exist
    public boolean checkExistenceOfApplication(Long applicationId){
        Iterator<Application> iterator = applications.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId().equals(applicationId) ){
                return true;
            }
        }
        return false;
    }

    public boolean checkExistenceOfApplicationByType(String applicationType){
        Iterator<Application> iterator = applications.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getApplicationType().equals(applicationType)){
                return true;
            }
        }
        return false;
    }


    public void setAll(Student updatedStudent ){
        super.setAll(updatedStudent);
        this.academicYear = updatedStudent.getAcademicYear();
        this.birthDate = updatedStudent.getBirthDate();
        this.nationality = updatedStudent.getNationality();
        this.gender = updatedStudent.getGender();
    }

    /**
     * Methods related to applications
     * */

    public void addApplication(Application application){
        applications.add(application);
    }

    public Application getApplicationByType(String applicationType){
        Iterator<Application> iterator = applications.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getApplicationType().equals(applicationType)){
                return iterator.next();
            }
        }
        return null;
    }

    public Application getApplicationById(Long applicationId){
        Iterator<Application> iterator = applications.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId().equals(applicationId)){
                return iterator.next();
            }
        }
        return null;
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
            if(iterator.next().getApplicationType().equals(applicationType)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    //TODO check whether this works or not
    public boolean updateApplicationByApplicationType(String applicationType, Application application) {
        for (int i = 0; i < applications.size(); i++){
            if(applications.get(i).getApplicationType().equals(applicationType)){
                applications.get(i).setAll(application);
                return true;
            }
        }
        return false;
    }

    public boolean updateApplicationByApplicationId(Long applicationId, Application application) {
        for (int i = 0; i < applications.size(); i++){
            if(applications.get(i).getId().equals(applicationId)){
                applications.get(i).setAll(application);
                return true;
            }
        }
        return false;
    }

    public Application getApplicationByApplicationType(String applicationType) {
        for (int i = 0; i < applications.size(); i++){
            if(applications.get(i).getApplicationType().equals(applicationType)){
                return applications.get(i);
            }
        }
        return null;
    }

    public Application getApplicationByApplicationId(Long applicationId) {
        for (int i = 0; i < applications.size(); i++){
            System.out.println(applications.get(i).getId() +  "  AA  " + applicationId);
            if(applications.get(i).getId().equals(applicationId)){
                return  applications.get(i);
            }
        }
        return null;
    }

    public boolean hasThisTypeApplication(String applicationType) {
        Iterator<Application> iterator = applications.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getApplicationType().equals(applicationType)){
                return true;
            }
        }
        return false;
    }
}
