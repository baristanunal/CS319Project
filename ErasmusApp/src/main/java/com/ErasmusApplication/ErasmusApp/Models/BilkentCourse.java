package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BilkentCourse extends Course {

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "bilkent_courses_prerequisites",
            joinColumns = @JoinColumn(name = "bilkent_course_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_bilkent_course_id"))
    private List<BilkentCourse> bilkentCourse;


    @ManyToMany(mappedBy = "bilkentCourse")
    private List<BilkentCourse> prerequisites;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "corresponding_bilkent-host_courses",
            joinColumns = @JoinColumn(name = "bilkent_course_id"),
            inverseJoinColumns = @JoinColumn(name = "host_course_id"))
    private List<HostCourse> correspondingHostCourses;


    @JsonIgnore
    @OneToMany(
            mappedBy = "bilkentCourse",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Wish> wishes;

    public List<HostCourse> getCorrespondingHostCourses() {
        return correspondingHostCourses;
    }

    public void setCorrespondingHostCourses(List<HostCourse> correspondingHostCourses) {
        this.correspondingHostCourses = correspondingHostCourses;
    }


//    List<BilkentCourse> prerequisites;


    public BilkentCourse(Double ECTS_credit, String nameOfCourse, String courseCode) {
        super(ECTS_credit, nameOfCourse, courseCode);
    }

    public BilkentCourse() {
    }
}
