package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity(name = "Task")
@Table(name = "Task")
public class Task {
    // Properties
    private String content;
    private String deadline;
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "task_sequence"
    )
    private Long Id;

    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY)
    private UserClass user;


    // Constructors

    // Default Constructor
    public Task() {

    }

    public Task(String content, String deadline,  Long Id) {
        this.content = content;
        this.deadline = deadline;
        this.Id = Id;
    }

    public Task(String content, String deadline ) {
        this.content = content;
        this.deadline = deadline;
    }

    // Methods

}
