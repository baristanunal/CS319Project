package com.ErasmusApplication.ErasmusApp.Models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//@MappedSuperclass
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserClass {

//    @OneToMany(
//            mappedBy = "user",
//            orphanRemoval = true,
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//            fetch = FetchType.LAZY
////            cascade = CascadeType.ALL
//    )
//    @OneToMany(mappedBy = "user")
//    @JoinColumn(name = "userId")
//    @OneToMany(mappedBy = "user")
//    private List<Task> tasks2;
    private String email2;
    private String firstName2;
    private String lastName2;

    @Id
    private Long UserId; //TODO







//    public void removeTask(Task task) {
//        tasks.remove(task);
//    }
//    public void addTask(Task newTask){
//        tasks.add(newTask);
//    }



}

