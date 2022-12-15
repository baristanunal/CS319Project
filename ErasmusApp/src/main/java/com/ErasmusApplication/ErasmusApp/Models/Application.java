package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.Data;
import org.apache.catalina.Host;

import java.util.Iterator;
import java.util.List;

@Data
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @JsonIgnore
    @ManyToOne( fetch = FetchType.LAZY)
    private Student student;
    private boolean isPlaced;
    private boolean isInWaitingBin;

    @JsonIgnore
    @ManyToOne
    private HostUniversity placedHostUniversity;
    private String applicationType;
    private Double totalPoints;
    //    private Transcript transcript;
    private String appliedAcademicSemester;
    @JsonIgnore
    @ManyToMany
    private List<HostUniversity> preferredUniversities = new java.util.ArrayList<>(); //TODO solve this problem, where to store it


    @JsonIgnore
    @OneToMany(
            mappedBy = "application",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Form> forms;

    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private CourseWishList courseWishList;


    public Application(Student student, boolean isPlaced, boolean isInWaitingBin, HostUniversity placedHostUniversity, String applicationType, Double totalPoints, String appliedAcademicSemester) {
        this.student = student;
        this.isPlaced = isPlaced;
        this.isInWaitingBin = isInWaitingBin;
        this.placedHostUniversity = placedHostUniversity;
        this.applicationType = applicationType;
        this.totalPoints = totalPoints;
        this.appliedAcademicSemester = appliedAcademicSemester;
    }

    public Application(Student student, boolean isPlaced, boolean isInWaitingBin, List<HostUniversity> preferredUniversities, HostUniversity placedHostUniversity, String applicationType, Double totalPoints, String appliedAcademicSemester){
      this.student = student;
      this.isPlaced = isPlaced;
      this.isInWaitingBin = isInWaitingBin;
      this.placedHostUniversity = placedHostUniversity;
      this.preferredUniversities = preferredUniversities;
      this.applicationType = applicationType;
      this.totalPoints = totalPoints;
      this.appliedAcademicSemester = appliedAcademicSemester;
    }

    public Application() {
    }



    /**
    * This method sets all properties, except relational objects : @OneToMany @OneToOne @ManyToOne  etc.
     * */
    public void setAll(Application application){
        this.isPlaced = application.isPlaced;
        this.isInWaitingBin = application.isInWaitingBin;
        this.preferredUniversities = application.preferredUniversities;
        this.placedHostUniversity = application.placedHostUniversity;
        this.applicationType = application.applicationType;
        this.totalPoints = application.totalPoints;
        this.appliedAcademicSemester = application.appliedAcademicSemester;
    }


    public void removePreferredUniversity(HostUniversity preferredUniversity) {
        preferredUniversities.remove(preferredUniversity);
    }
    public void addPreferredUniversity(HostUniversity preferredUniversity){
        preferredUniversities.add(preferredUniversity);
    }
    public void removePreferredUniversityByName(String universityName) {
        Iterator<HostUniversity> iterator = preferredUniversities.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getNameOfInstitution().equals(universityName) ){
                iterator.remove();
            }
        }

    }


    //TODO if is not placed the we should not get hostUniversity
    // Possible solution: If the student is not placed, then we can make HostUniversity null.
}
