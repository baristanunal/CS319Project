package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
public class WaitingBin {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @OneToOne(orphanRemoval = true)
    @MapsId
    private PlacementManager placementManager;

    //TODO determine whether keep students or applications
    @OneToMany(
            mappedBy = "waitingBin"
    )
    List<Application> applications = new ArrayList<>();

    public PlacementManager getPlacementManager() {
        return placementManager;
    }

    public void setPlacementManager(PlacementManager placementManager) {
        this.placementManager = placementManager;
    }

    public void addApplications( List<Application> newApplications ){
      applications.addAll(newApplications);
    }

    public List<Application> getApplications() {
        Collections.sort(applications, (s1, s2) -> s1.getId().compareTo(s2.getId()) > 1 ? 1 : s1.getId().compareTo(s2.getId()) < 1 ? -1 : 0);
        return applications;
    }

    //TODO Add method to Create Update Remove List object
}
