package com.ErasmusApplication.ErasmusApp.Models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@MappedSuperclass
@Table(name = "UserClass")

public class UserClass {
    // Properties



    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Task> tasks;
    private String email;
    private String firstName;
    private String lastName;
    private Long schoolId;
    @Id
    private Long Id; //TODO


    // Constructors



    // Methods
    //    public void removeTask(Task task) {
    //        tasks.remove(task);
    //    }
    //    public void addTask(Task newTask){
    //        tasks.add(newTask);
    //    }



}

