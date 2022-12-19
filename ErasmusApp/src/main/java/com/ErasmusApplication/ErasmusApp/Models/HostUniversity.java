package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import org.apache.catalina.Host;

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
    @OneToMany(mappedBy = "placedHostUniversity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> placedApplications ;
    @JsonIgnore
    @OneToMany(mappedBy = "hostUniversity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HostCourse> hostCourses ;
    @JsonIgnore
    @OneToMany(mappedBy = "hostUniversity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HostUniversityDepartment> departments = new ArrayList<>();

    public HostUniversity( String nameOfInstitution ){
      this.nameOfInstitution = nameOfInstitution;
    }

    public HostUniversity() {
    }

    public HostUniversity( String nameOfInstitution, List<HostCourse> hostCourses, List<HostUniversityDepartment> departments ){
      this.nameOfInstitution = nameOfInstitution;
      this.hostCourses = hostCourses;
      this.departments = departments;
    }

    public HostUniversity( String nameOfInstitution, List<HostUniversityDepartment> departments ){
      this.nameOfInstitution = nameOfInstitution;
      this.departments = departments;
    }

    public boolean addPlacedApplication(Application app){
        placedApplications.add(app);
        return true;
    }
}
