package com.ErasmusApplication.ErasmusApp.Models;


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


    @JsonManagedReference
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
    private Long Id; //TODO


    public UserClass(String email, String firstName, String lastName, long schoolId) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolId = schoolId;
    }
    // Constructors

    public UserClass() {

    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }
    public void addTask(Task newTask){
        tasks.add(newTask);
    }
    public void removeTaskById(Long taskId) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId() == taskId){
                iterator.remove();
            }
        }

    }
    // Methods
    //    public void removeTask(Task task) {
    //        tasks.remove(task);
    //    }
    //    public void addTask(Task newTask){
    //        tasks.add(newTask);
    //    }



}

