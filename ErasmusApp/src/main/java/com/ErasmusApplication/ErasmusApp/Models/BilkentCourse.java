package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BilkentCourse extends Course {

    @ManyToMany
    @JoinTable(name = "bilkent_courses_prerequisites",
            joinColumns = @JoinColumn(name = "bilkent_course_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_bilkent_course_id"))
    private List<BilkentCourse> bilkentCourse;


    @ManyToMany(mappedBy = "bilkentCourse")
    private List<BilkentCourse> prerequisites;

    @ManyToMany
    @JoinTable(name = "corresponding_bilkent-host_courses",
            joinColumns = @JoinColumn(name = "bilkent_course_id"),
            inverseJoinColumns = @JoinColumn(name = "host_course_id"))
    private List<HostCourse> correspondingHostCourses;

    public List<HostCourse> getCorrespondingHostCourses() {
        return correspondingHostCourses;
    }

    public void setCorrespondingHostCourses(List<HostCourse> correspondingHostCourses) {
        this.correspondingHostCourses = correspondingHostCourses;
    }


//    List<BilkentCourse> prerequisites;

}
