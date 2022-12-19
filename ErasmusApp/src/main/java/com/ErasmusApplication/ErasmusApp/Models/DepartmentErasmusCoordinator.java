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

    public DepartmentErasmusCoordinator( String firstName, String lastName, String schoolId, String faculty, String department, String role, String email, String password) {
      super( firstName, lastName, schoolId, faculty, department, role, email, password );
    }

    public DepartmentErasmusCoordinator() {

    }
}
