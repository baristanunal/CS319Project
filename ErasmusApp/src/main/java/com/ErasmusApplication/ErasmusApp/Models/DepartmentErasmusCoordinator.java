package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    public DepartmentErasmusCoordinator(String email, String firstName, String lastName, long schoolId) {
        super(email, firstName, lastName, schoolId);
    }

    public DepartmentErasmusCoordinator() {

    }
}
