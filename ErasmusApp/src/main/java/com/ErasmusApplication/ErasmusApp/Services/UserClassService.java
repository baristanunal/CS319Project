package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.*;
import com.ErasmusApplication.ErasmusApp.Repositories.UserClassRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.*;

//@RequiredArgsConstructor
@Service  @Transactional @AllArgsConstructor
public class UserClassService  { //implements UserDetailsService

    //TODO  add erasmus manager with its implementation
    //    ErasmusManager erasmusManager;
    private final UserClassRepository userClassRepository;
    private final TaskService taskService;

    /**
     * Methods for login of Users
     */
    public User loginCheck(String id){
        UserClass user = userClassRepository.findBySchoolIdOpt(id)
                .orElseThrow( () -> new UsernameNotFoundException("No user was found!!"));

        return new User(user.getSchoolId(),user.getPassword(),user.getAuthorities());
    }


    public UserClass updatePassword(Long userId) {
        UserClass user = getUser(userId);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return user;
    }
    public UserClass registerUser(UserClass user, String roleName){
        UserClass userFromDatabase = saveUser(user);

        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(roleName);
        return user;
    }
    //TODO delete this just after you are sure that auth works fine
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        //We userd schooldId as username so
//
//        UserClass user = getUserBySchoolId(username);
//        return new User(user.getSchoolId(),user.getPassword(),user.getRoles());
//    }
    /**
     * Methods for CRUD of Users
     */

    /**
     * This method is to save users. We will not use this frequently. We will use saveStudent saveCourseCoordinator etc.
     * @param user to save
     * @return saved user
     */
    public UserClass saveUser(UserClass user) {
        Optional<UserClass> userBySchoolId = userClassRepository.findBySchoolIdOpt(user.getSchoolId());
        if( userBySchoolId.isPresent()){
            throw new IllegalStateException("School Id is taken!");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userClassRepository.save(user);
    }

    public UserClass getUser(Long userId) {

        return userClassRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "User With Id: " + userId + " does not exist."
        ));
    }

    public UserClass getUserBySchoolId(String schoolId) {
        UserClass user = userClassRepository.findBySchoolId(schoolId);
        return user;
    }

    public List<UserClass> getUsers(){
        return userClassRepository.findAll();
    }

    public void updateStudent(Long userId,UserClass updatedUser){
        UserClass user = getUser(userId);
        user.setAll(updatedUser);
    }

    public void deleteUser(Long userId) {
        //TODO add cornercase
        boolean exist = userClassRepository.existsById(userId);
        if(!exist){
            throw new IllegalStateException("User with Id: " + userId + " does not exist!");
        }
        userClassRepository.deleteById(userId);
    }


    /**
     * Methods related to tasks
     */
    public UserClass addTaskToUser(Long userId, Task newTask) {
        UserClass user = getUser(userId);
        newTask.setUser(user);
        boolean success = user.addTask(newTask);

        //TODO
        if (!success) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Failed to add task to Student with Id: " + userId)
            );
        }
        return user;//TODO
    }
    public List<Task> getAllTasks(Long userId){
        UserClass user = getUser(userId);
        List<Task> tasks = user.getTasks();

        //TODO  return empty list
        if (tasks.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "user with With Id: " + userId + " does not have any tasks"
                    ));
        }
        return tasks;

    }

    @Transactional
    public UserClass addTasks(Long userId, Task taskToUpdate) {
        //TODO  to obrtain addition of list you need to set all
        UserClass user = getUser(userId);
        Task task = taskService.addNewTask(taskToUpdate);
        task.setUser(user);
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        user.addTasks(tasks);

        return user;
    }

    public UserClass updateTask(Long userId, Long taskId, Task taskToUpdate) {
        UserClass user = getUser(userId);
        boolean isExist = user.updateTaskByTaskId(taskId,taskToUpdate);

        if (!isExist){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Task with Id: " + taskId + " is not belong to User with Id: " +userId)
            );
        }

        return user;
    }
    public UserClass removeTaskFromUser(Long userId, Long taskId) {
        //TODO check whether this task belongs to this user
        taskService.deleteTask(taskId);
        UserClass user = getUser(userId);
        return user;
    }


    /**
     * Methods related to roles
     */

    public void addRoleToUser(String schoolId, String roleName) {
        UserClass user = getUserBySchoolId(schoolId);
        user.setRole(roleName);
    }
    public void addRoleToUserById(Long userId, String roleName) {
        UserClass user = getUser(userId);
        user.setRole(roleName);
    }
    public List<DepartmentErasmusCoordinator> getCoordinatorsByDepartment( String departmentName ){
        return userClassRepository.findByDepartmentAndRole( departmentName, "depCoordinator" );
    }

}