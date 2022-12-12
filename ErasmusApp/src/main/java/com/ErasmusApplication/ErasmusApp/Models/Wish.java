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
    private CourseWishList courseWishList;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "wish_forms",
            joinColumns = @JoinColumn(name = "wish_id"),
            inverseJoinColumns = @JoinColumn(name = "form_id"))
    private List<Form> forms;

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }


    // Constructors

    // Methods
}
