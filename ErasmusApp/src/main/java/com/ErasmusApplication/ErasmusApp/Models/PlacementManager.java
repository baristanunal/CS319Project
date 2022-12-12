package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Data
public class PlacementManager {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    @JsonIgnore
    @OneToMany(
            mappedBy = "placementManager",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DepartmentErasmusCoordinator> departmentErasmusCoordinators;

    @OneToOne(mappedBy = "placementManager", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private PlacementTable placementTable;

    @OneToOne(mappedBy = "placementManager", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private WaitingBin waitingBin;



    public PlacementManager(List<DepartmentErasmusCoordinator> departmentErasmusCoordinators) {
        this.departmentErasmusCoordinators = departmentErasmusCoordinators;
    }

    public PlacementManager() {

    }


    //TODO Add method to Create Update Remove List object


}
