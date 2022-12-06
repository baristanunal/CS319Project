package com.ErasmusApplication.ErasmusApp.Models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
@MappedSuperclass
public abstract class User {

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "userId")
    private List<Task> tasks;
    private String email;
    private String firstName;
    private String lastName;

    @Id
    private Long userId; //TODO

    public User() {
    }


    public User( String email, String firstName, String lastName, Long userId) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }



//    public void removeTask(Task task) {
//        tasks.remove(task);
//    }
//    public void addTask(Task newTask){
//        tasks.add(newTask);
//    }



}

