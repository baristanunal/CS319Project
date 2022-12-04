package com.ErasmusApplication.ErasmusApp.Models;

import lombok.Data;

import java.util.ArrayList;

@Data
public abstract class User {

    private ArrayList<Task> tasks;
    private String email;
    private String firstName;
    private String lastName;

    private Long ID; //TODO

    public User() {
    }

    public User(String email, String firstName, String lastName, Long ID) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
    }

    public User(ArrayList<Task> tasks, String email, String firstName, String lastName, long ID) {
        this.tasks = tasks;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
    }



    public void removeTask(Task task) {
        tasks.remove(task);
    }
    public void addTask(Task newTask){
        tasks.add(newTask);
    }



}

