package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.*;
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

    @ManyToMany(mappedBy = "correspondingHostCourses")
    private List<BilkentCourse> correspondingBilkentCourses;

    @ManyToMany
    @JoinTable(name = "host_course_wishes",
            joinColumns = @JoinColumn(name = "host_course_id"),
            inverseJoinColumns = @JoinColumn(name = "wishes_id"))
    private List<Wish> wishes = new ArrayList<>();

    @ManyToOne
    private HostUniversity hostUniversity;


}
