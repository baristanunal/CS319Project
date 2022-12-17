package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Wish {
//     Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String intent;
    private String standing;
    private String syllabus;

    @JsonIgnore
    @ManyToOne
    private BilkentCourse bilkentCourse;

    @JsonIgnore
    @ManyToOne
    private HostCourse courseToCountAsBilkentCourse;


    @JsonIgnore
    @ManyToOne
    private CourseWishList courseWishList;


    public Double getHostCourseCredit(){
        if( courseToCountAsBilkentCourse == null){
            return 0.0;
        }
        return courseToCountAsBilkentCourse.getECTS_credit();
    }

    public void setAll(Wish wish) {
        this.intent = wish.getIntent();
        this.standing = wish.getStanding();
        this.syllabus = wish.getSyllabus();
    }

    // Constructors

    // Methods
}
