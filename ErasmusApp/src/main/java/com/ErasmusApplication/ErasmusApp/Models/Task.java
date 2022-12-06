package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity(name = "Task")
public class Task {
    private String content;
    private String deadline;
    @Id
//    @SequenceGenerator(
//            name = "task_sequence",
//            sequenceName = "task_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = SEQUENCE,
//            generator = "task_sequence"
//    )
    private Long Id;

//    @ManyToOne
//    private User user;
//    @ManyToOne
//    @JoinColumn(
//            name = "student_id",
//            nullable = false,
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "student_book_fk"
//            )
//    )
//    private User user;



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

}
