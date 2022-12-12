package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PassingGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passing_grade_seq")
    @SequenceGenerator(name = "passing_grade_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne( fetch = FetchType.LAZY)
    private CourseTransfer courseTransfer;

    private String grade;

    public CourseTransfer getCourseTransfer() {
        return courseTransfer;
    }

    public void setCourseTransfer(CourseTransfer courseTransfer) {
        this.courseTransfer = courseTransfer;
    }


}