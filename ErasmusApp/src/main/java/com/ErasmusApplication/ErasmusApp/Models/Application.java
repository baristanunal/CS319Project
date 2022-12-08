package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Iterator;
import java.util.List;

@Data
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY)
    private Student student;
    private boolean isPlaced;
    private String studyCycle;
    @Transient
    private List<String> preferredUniversities; //TODO solve this problem, where to store it
    private String hostUniversity;
    private String applicationType;
    private String appliedAcademicSemester;
    public Application() {
    }

    public Application(Student student, boolean isPlaced, String studyCycle, List<String> preferredUniversities, String hostUniversity, String applicationType, String appliedAcademicSemester) {
        this.student = student;
        this.isPlaced = isPlaced;
        this.studyCycle = studyCycle;
        this.preferredUniversities = preferredUniversities;
        this.hostUniversity = hostUniversity;
        this.applicationType = applicationType;
        this.appliedAcademicSemester = appliedAcademicSemester;
    }


    //TODO  remove all above accordingto your choice about List<String> vs. Universities object etc.
    public void removePrefferedUniversity(String preferredUniversity) {
        preferredUniversities.remove(preferredUniversity);
    }
    public void addPrefferedUniversity(String preferredUniversity){
        preferredUniversities.add(preferredUniversity);
    }
    public void removePrefferedUniversityByName(String universityName) {
        Iterator<String> iterator = preferredUniversities.iterator();
        while (iterator.hasNext()) {
            if(iterator.next() == universityName){
                iterator.remove();
            }
        }

    }

    //    private Transcript transcript;
//    private Form[] forms;
}
