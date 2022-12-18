package com.ErasmusApplication.ErasmusApp.Models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class HostCourse extends Course{
    private boolean isApproved;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "correspondingHostCourses")
//    private List<BilkentCourse> correspondingBilkentCourses;

    @JsonIgnore
    @OneToMany(
            mappedBy = "courseToCountAsBilkentCourse",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Wish> wishes;


    @JsonIgnore
    @ManyToOne
    private HostUniversity hostUniversity;


    public HostCourse() {
    }
    public HostCourse(Double ECTS_credit, String nameOfCourse, String courseCode) {
        super(ECTS_credit, nameOfCourse, courseCode);
    }
}
