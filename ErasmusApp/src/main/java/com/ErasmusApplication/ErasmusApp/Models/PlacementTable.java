package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
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

    //TODO determine whether keep students or applications
    @JsonIgnore
    @OneToMany(
            mappedBy = "placementTable"
    )
    List<Application> applications;

    private int a;

    public PlacementTable() {
      a = 3;
    }

  public PlacementTable( List<Application> mainList ) {

  }

    public PlacementManager getPlacementManager() {
        return placementManager;
    }

    public void setPlacementManager(PlacementManager placementManager) {
        this.placementManager = placementManager;
    }

    public void addApplications( List<Application> newApplications ){
      applications.addAll(newApplications);
    }




}
