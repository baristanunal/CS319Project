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
    private String hostCourseName;


//    @JsonIgnore
//    @ManyToMany(mappedBy = "correspondingHostCourses")
//    private List<BilkentCourse> correspondingBilkentCourses;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "host_course_wishes",
            joinColumns = @JoinColumn(name = "host_course_id"),
            inverseJoinColumns = @JoinColumn(name = "wishes_id"))
    private List<Wish> wishes = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    private HostUniversity hostUniversity;


    public HostCourse() {
    }
    public HostCourse(Double ECTS_credit, String nameOfCourse, String courseCode, String courseType) {
        super(ECTS_credit, nameOfCourse, courseCode, courseType);
    }
}
