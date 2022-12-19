package com.ErasmusApplication.ErasmusApp.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class HostUniversityDepartment {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private Long id;

  @JsonIgnore
  @ManyToOne( fetch = FetchType.LAZY)
  HostUniversity hostUniversity;

  int quota;
  String departmentName;

  public HostUniversityDepartment(){

  }

  public HostUniversityDepartment( String departmentName, int quota ){
    this.departmentName = departmentName;
    this.quota = quota;
  }

}
