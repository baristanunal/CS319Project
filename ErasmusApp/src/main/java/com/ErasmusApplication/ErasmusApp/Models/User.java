package com.ErasmusApplication.ErasmusApp.Models;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

@Data
@MappedSuperclass
public abstract class User {

//    @OneToMany
//    private ArrayList<Task> tasks;
    private String email;
    private String firstName;
    private String lastName;

    @Id
    private Long ID; //TODO

    public User() {
    }


    public User( String email, String firstName, String lastName, Long ID) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
    }



//    public void removeTask(Task task) {
//        tasks.remove(task);
//    }
//    public void addTask(Task newTask){
//        tasks.add(newTask);
//    }



}

