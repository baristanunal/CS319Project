package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class DepartmentErasmusCoordinator extends UserClass {

    // Properties
    @JsonIgnore
    @ManyToOne( fetch = FetchType.LAZY)
    private PlacementManager placementManager;



// Constructors

    public DepartmentErasmusCoordinator(PlacementManager placementManager) {
        this.placementManager = placementManager;
    }

    public DepartmentErasmusCoordinator(String email, String firstName, String lastName, String schoolId, String faculty, String department,String password) {
        super(email, firstName, lastName, schoolId, faculty, department,password);
    }

    public DepartmentErasmusCoordinator() {

    }
}
