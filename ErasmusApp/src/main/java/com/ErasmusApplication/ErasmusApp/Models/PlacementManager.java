package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
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
            mappedBy = "placementManager"
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
