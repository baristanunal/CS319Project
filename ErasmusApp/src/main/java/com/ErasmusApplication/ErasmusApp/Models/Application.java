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

    @ManyToOne( )
    private HostUniversity placedHostUniversity;
    @JsonIgnore
    @Transient
    private List<String> preferredUniversities; //TODO solve this problem, where to store it

//    @JsonIgnore
//    @OneToMany(
//            mappedBy = "application",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Form> forms;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  private WaitingBin waitingBin;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  private PlacementTable placementTable;



    @JsonIgnore
    @OneToOne(mappedBy = "application",cascade = CascadeType.ALL, orphanRemoval = true)
//    @PrimaryKeyJoinColumn
    private CourseWishList courseWishlist;


    private String applicationType;

    private Double totalPoints;
    private String appliedAcademicSemester;
    private boolean isPlaced;
    private boolean isInWaitingBin;
    @Transient
    private String name;


    public Application(Student student, boolean isPlaced, boolean isInWaitingBin, HostUniversity placedHostUniversity, String applicationType, Double totalPoints, String appliedAcademicSemester) {
        this.student = student;
        this.isPlaced = isPlaced;
        this.isInWaitingBin = isInWaitingBin;
        this.placedHostUniversity = placedHostUniversity;
        this.applicationType = applicationType;
        this.totalPoints = totalPoints;
        this.appliedAcademicSemester = appliedAcademicSemester;
    }

    public Application(Student student, boolean isPlaced, boolean isInWaitingBin, List<String> preferredUniversities, HostUniversity placedHostUniversity, String applicationType, Double totalPoints, String appliedAcademicSemester){
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
        this.isPlaced = false;
        this.isInWaitingBin = true;
    }

  String getName(){
    return student.getFirstName();
  }


    public boolean checkExistenceOfPreferredUni(String hostUniName){
        Iterator<String> iterator = preferredUniversities.iterator();
        while (iterator.hasNext()) {
            if(  iterator.next().equals(hostUniName) ){
                return true;
            }
        }
        return false;
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

    public String getNameOfPlacedHostUniversity(){
        if(placedHostUniversity == null){
            return "not placed";
        }
        return placedHostUniversity.getNameOfInstitution();
    }

    public void addPreferredUniversity(String preferredUniversity){
        preferredUniversities.add(preferredUniversity);
    }
    public void addAllPreferredUniversities(List<String> preferredUniversity){
        for ( String uni: preferredUniversity) {
            preferredUniversities.add(uni);
        }
    }
    public void removePreferredUniversity(HostUniversity preferredUniversity) {
        preferredUniversities.remove(preferredUniversity);
    }
    public void removePreferredUniversityByName(String universityName) {
        Iterator<String> iterator = preferredUniversities.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().equals(universityName) ){
                iterator.remove();
            }
        }
    }



    //TODO if is not placed the we should not get hostUniversity
    // Possible solution: If the student is not placed, then we can make HostUniversity null.


    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }
}
