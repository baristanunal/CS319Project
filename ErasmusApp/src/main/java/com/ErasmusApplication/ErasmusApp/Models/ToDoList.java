package com.ErasmusApplication.ErasmusApp.Models;

import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> tasks;

    public ToDoList() {
    }

    public ToDoList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
    }
    public ArrayList<Task>getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
