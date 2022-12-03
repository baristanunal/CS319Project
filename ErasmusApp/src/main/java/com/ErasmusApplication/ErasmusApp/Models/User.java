package com.ErasmusApplication.ErasmusApp.Models;

public abstract class User {
    private ToDoList toDoList;
    private String email;
    private String firstName;
    private String lastName;
    private ErasmusManager erasmusManager;
    int ID;

    public User(ToDoList toDoList, String email, String firstName, String lastName, ErasmusManager erasmusManager, int ID) {
        this.toDoList = toDoList;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.erasmusManager = erasmusManager;
        this.ID = ID;
    }

    public User() {
    }
}

