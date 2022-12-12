package com.ErasmusApplication.ErasmusApp.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@MappedSuperclass
@Table(name = "UserClass")

public class UserClass {
    // Properties


    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Task> tasks;
    private String email;
    private String firstName;
    private String lastName;
    private long schoolId;
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "id", nullable = false)
    private Long id; //TODO

    // Constructors
    public UserClass(String email, String firstName, String lastName, long schoolId) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolId = schoolId;
    }

    public UserClass() {

    }

    public boolean checkExistenceOfTask(Long taskId){
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId() == taskId){
                return true;
            }
        }
        return false;
    }
    //TODO useless delete
    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }
    public boolean addTask(Task newTask){
        return tasks.add(newTask);
    }
    public boolean removeTaskById(Long taskId) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId() == taskId){
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    public Task getTaskById(Long taskId) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId() == taskId){
               return iterator.next();
            }
        }
        return null;
    }

    public boolean updateTaskByTaskId(Long taskId, Task task) {
        for (int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getId() == taskId){
                tasks.get(i).setAll(task);
                return true;
            }
        }
        return false; // if task does not exist in User return false
    }

}

