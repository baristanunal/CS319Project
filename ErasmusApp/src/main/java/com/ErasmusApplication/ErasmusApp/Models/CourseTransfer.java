package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Entity
@Data
public class CourseTransfer extends Form {
    // Properties
    private boolean signedByDean;
    private boolean signedByDepChair;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "courseTransfer")
    private List<PassingGrade> passingGrades = new java.util.ArrayList<>();


    // Constructors


    //TODO Add method to Create Update Remove List object
}
