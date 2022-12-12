package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Wish {
    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String intent;
    //TODO do smth for these
    private String standing;
    private String syllabus;


    @JsonIgnore
    @ManyToOne
    private BilkentCourse bilkentCourse;

    @ManyToMany(mappedBy = "wishes")
    private List<HostCourse> courseToCountAsBilkentCourse = new ArrayList<>();

//    private List<HostCourse> courseToCountAsBilkentCourse;

    @JsonIgnore
    @ManyToOne
    private CourseWishList courseWishList;




    // Constructors

    // Methods
}
