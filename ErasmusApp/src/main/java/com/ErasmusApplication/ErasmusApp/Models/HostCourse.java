package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

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

}
