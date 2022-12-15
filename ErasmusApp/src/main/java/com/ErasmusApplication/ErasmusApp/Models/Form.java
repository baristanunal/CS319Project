package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    // Properties
    private boolean isApproved;
    private boolean signedByDepCoordinator;

    @JsonIgnore
    @ManyToOne( fetch = FetchType.LAZY)
    private Application application;

    //TODO add relation
    //TODO Add method to Create Update Remove List object
    @JsonIgnore
    @ManyToOne
    private CourseWishList courseWishList;


//    private List<Wish> wishes;


    public Application getApplication() {
        return application;
    }
    public void setApplication(Application application) {
        this.application = application;
    }


    public Form() {
    }
}
