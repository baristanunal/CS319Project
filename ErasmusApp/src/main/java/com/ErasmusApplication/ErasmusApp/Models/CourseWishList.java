package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CourseWishList {
    // Properties

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @OneToMany(
            mappedBy = "courseWishList",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Wish> wishes;
    private boolean isCompleted;
    //TODO @Transient
    private Double totalCredit;

    @JsonIgnore
    @OneToMany(
            mappedBy = "courseWishList",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Form> forms;

    @OneToOne(orphanRemoval = true)
    @MapsId
    private Application application;

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseWishList() {
    }

}
