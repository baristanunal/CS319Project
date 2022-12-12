package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PlacementTable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne(orphanRemoval = true)
    @MapsId
    private PlacementManager placementManager;

    @JsonIgnore
    @OneToMany(
            mappedBy = "placementTable"
    )
    List<Student> placements;

    public PlacementManager getPlacementManager() {
        return placementManager;
    }

    public void setPlacementManager(PlacementManager placementManager) {
        this.placementManager = placementManager;
    }
}
