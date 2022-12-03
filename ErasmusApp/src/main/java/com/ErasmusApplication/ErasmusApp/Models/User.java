package com.ErasmusApplication.ErasmusApp.Models;

import java.util.ArrayList;

public abstract class User {
    private ArrayList<Task> tasks;
    private String email;
    private String firstName;
    private String lastName;
    private ErasmusManager erasmusManager;
    private Long ID;

    public User() {
    }

    public User(String email, String firstName, String lastName, ErasmusManager erasmusManager, Long ID) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.erasmusManager = erasmusManager;
        this.ID = ID;
    }

    public User(ArrayList<Task> tasks, String email, String firstName, String lastName, ErasmusManager erasmusManager, long ID) {
        this.tasks = tasks;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.erasmusManager = erasmusManager;
        this.ID = ID;
    }



    public void removeTask(Task taskId){

    }
    public void addTask(Task newTask){
        tasks.add(newTask);
    }





    // Getters Setters
    public ArrayList<Task> getToDoList() {
        return tasks;
    }

    public void setToDoList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ErasmusManager getErasmusManager() {
        return erasmusManager;
    }

    public void setErasmusManager(ErasmusManager erasmusManager) {
        this.erasmusManager = erasmusManager;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "User{" +
                "tasks=" + tasks +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", erasmusManager=" + erasmusManager +
                ", ID=" + ID +
                '}';
    }
}

