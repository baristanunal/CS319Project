package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Task {
    private String content;
    private String deadline;


    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    private Long taskId;

    public Task() {

    }

    public Task(String content, String deadline ) {
        this.content = content;
        this.deadline = deadline;
    }

}
