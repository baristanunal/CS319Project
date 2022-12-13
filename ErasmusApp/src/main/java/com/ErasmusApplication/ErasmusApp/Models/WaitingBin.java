package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class WaitingBin {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne(orphanRemoval = true)
    @MapsId
    private PlacementManager placementManager;

    //TODO determine whether keep students or applications
    @JsonIgnore
    @OneToMany(
            mappedBy = "waitingBin"
    )
    List<Student> waitingStudents;

    public PlacementManager getPlacementManager() {
        return placementManager;
    }

    public void setPlacementManager(PlacementManager placementManager) {
        this.placementManager = placementManager;
    }

    //TODO Add method to Create Update Remove List object
}
