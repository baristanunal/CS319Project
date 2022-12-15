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

    //TODO Add method to Create Update Remove List object
    @JsonIgnore
    @ManyToOne
    private CourseWishList courseWishList;

    @OneToOne(orphanRemoval = true)
    @MapsId
    private Application application;

    private boolean isApproved;
    private boolean signedByDepCoordinator;
    private String nameOfFile;

    public Form() {
    }
}
