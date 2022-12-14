package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.Inheritance;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.SEQUENCE;


//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@MappedSuperclass

@Data@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "UserClass")
public class UserClass  { //implements UserDetails
    // Properties

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
    private Long id;
    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Task> tasks;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
    private String email;
    private String firstName;
    private String lastName;
    private String schoolId;
    private String faculty;
    private String department;
    private String password;

    public UserClass(String email, String firstName, String lastName, String schoolId, String faculty, String department,String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolId = schoolId;
        this.faculty = faculty;
        this.department = department;
        this.password  = password;
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

    public boolean addRole(Role role){
        roles.add(role);
        return true;
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = getRoles().stream()
                .map(item -> new SimpleGrantedAuthority(item.getAuthority()))
                .collect(Collectors.toList());
        return roles;
    }

//    public Collection<? extends GrantedAuthority> getRoleNames() {
//        Collection<? extends GrantedAuthority> coll;
//        for (int i = 0; i < roles.size(); i++){
//            coll.add(roles);
//        }
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles;
//    }
//
//    @Override
//    public String getUsername() {
//        return schoolId;
//    }
//
//    //TODO below
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}

