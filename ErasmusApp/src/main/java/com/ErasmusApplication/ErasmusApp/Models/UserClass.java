package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.persistence.Inheritance;
import java.util.*;
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

  @OneToMany(
          mappedBy = "user",
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  private List<Task> tasks = new ArrayList<>();
  private String role;
  private String email;
  private String firstName;
  private String lastName;
  private String schoolId;
  private String faculty;
  private String department;
  @JsonIgnore
  private String password;

  public UserClass(String email, String firstName, String lastName, String schoolId, String faculty, String department,String password) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.schoolId = schoolId;
    this.faculty = faculty;
    this.department = department;
    this.password  = new BCryptPasswordEncoder().encode(password);
  }

  public UserClass( String firstName, String lastName, String schoolId, String faculty, String department, String role, String email, String password ){
    this.firstName = firstName;
    this.lastName = lastName;
    this.schoolId = schoolId;
    this.faculty = faculty;
    this.department = department;
    this.role = role;
    this.email = email;
    this.password = password;
  }

  public UserClass() {
  }

  public boolean checkExistenceOfTask(Long taskId){
    Iterator<Task> iterator = tasks.iterator();
    while (iterator.hasNext()) {
      if(iterator.next().getId().equals(taskId)){
        return true;
      }
    }
    return false;
  }


  public void setAll(UserClass user) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.schoolId = schoolId;
    this.faculty = faculty;
    this.department = department;
  }
  /**
   * Methods related to tasks
   * */
  public boolean addTask(Task newTask){
    return tasks.add(newTask);
  }
  public boolean addTasks(List<Task> newTasks){
    for (int i = 0; i < newTasks.size(); i++){
      tasks.add(newTasks.get(i));
    }
    return false;
  }
  public boolean removeTaskById(Long taskId) {
    Iterator<Task> iterator = tasks.iterator();
    while (iterator.hasNext()) {
      if(iterator.next().getId().equals(taskId)) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }

  public Task getTaskById(Long taskId) {
    Iterator<Task> iterator = tasks.iterator();
    while (iterator.hasNext()) {
      if(iterator.next().getId().equals(taskId)){
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

  /**
   * Methods related to roles
   */
  public void setRole(String newRole){
    role = newRole;
  }


  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (role == null || role.equals("")){
      return Collections.emptyList();
    }
    List<GrantedAuthority> grantedAuthorityList  = new ArrayList<>();
    List<String> listRoles = new ArrayList<>();
    listRoles.add(role);
    grantedAuthorityList = listRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    return grantedAuthorityList;
  }

}
