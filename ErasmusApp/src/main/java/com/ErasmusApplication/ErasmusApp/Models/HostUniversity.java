package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "host_university")
@Data
public class HostUniversity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "host_university_seq")
    @SequenceGenerator(name = "host_university_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    String nameOfInstitution;
    @JsonIgnore
    @ManyToMany(mappedBy = "preferredUniversities")
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "placedHostUniversity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> placedApplications = new ArrayList<>();

    @OneToMany(mappedBy = "hostUniversity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HostCourse> hostCourses = new ArrayList<>();

    @ManyToMany
    private List<HostUniversityDepartment> departments = new ArrayList<>();

    public HostUniversity( String nameOfInstitution ){
      this.nameOfInstitution = nameOfInstitution;
    }

    public HostUniversity() {
    }
}
