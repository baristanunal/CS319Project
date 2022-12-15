package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Models.Task;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentService {
//    ErasmusManager erasmusManager;
//    LoginManager loginManager;
    //TODO  add erasmus manager with its implementation

    StudentRepository studentRepository;
    TaskService taskService;

    ApplicationService applicationService;
    @Autowired
    public StudentService(StudentRepository studentRepository, TaskService taskService, ApplicationService applicationService) {
        this.studentRepository = studentRepository;
        this.taskService = taskService;
        this.applicationService = applicationService;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    public Student getStudent(Long userId){
        return studentRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with ID: " + userId + " does not exist."
                ));
    }

    public Student getStudentBySchoolId( String schoolId ){
        Student student = studentRepository.findBySchoolId(schoolId);
      if (student == null) {
        throw new IllegalStateException( "Student with school ID: "
          + schoolId + " does not exist in the database." );
      }
      else{
        return student;
      }
    }

    public Student saveStudent(Student student) {
        Optional<Student> userBySchoolId = studentRepository.findBySchoolIdOpt(student.getSchoolId());
        if( userBySchoolId.isPresent()){
            throw new IllegalStateException("School Id is taken!");
        }
        return studentRepository.save(student);
    }

    public void deleteUser(Long userId) {
        //TODO add cornercase
        boolean exist = studentRepository.existsById(userId);
        if(!exist){
            throw new IllegalStateException("User with Id: " + userId + " does not exist!");
        }
        studentRepository.deleteById(userId);
    }

    @Transactional
    public void updateStudent(Long userId,Student updatedStudent){
        Student student = getStudent(userId);


        student.setEmail(updatedStudent.getEmail());
        student.setFirstName(updatedStudent.getFirstName()); //TODO  could they?
        student.setLastName(updatedStudent.getLastName()); //TODO  could they?
        student.setSchoolId(updatedStudent.getSchoolId()); //TODO  could they?
        //TODO I probably understand this wrongly. What is academic year?  x.th semester or 21-22 stm like that. Why we need that value?
        student.setAcademicYear(updatedStudent.getAcademicYear()); //TODO how could be sure that student passed all these courses. Also, we need to store the starting date instead of the just the year.
        student.setBirthDate(updatedStudent.getBirthDate());
        student.setDepartment(updatedStudent.getDepartment());
        student.setGender(updatedStudent.getGender());
        student.setNationality(updatedStudent.getNationality());

    }

    //TASKS
    @Transactional
    public Student addTaskToStudent(Long userId, Task newTask) {
        Student student = getStudent(userId);
        newTask.setUser(student);
        boolean success = student.addTask(newTask);

        //TODO
        if (!success) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Failed to add task to Student with Id: " + userId)
            );
        }
        return student;//TODO
    }

    //TODO  move the remove part to TaskService  and handle the other operations there, CRUD
    @Transactional
    public Student removeTaskFromStudent(Long userId, Long taskId) {
        Student student = getStudent(userId);
        boolean success = student.removeTaskById(taskId);
        if (!success) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Student with Id: " + userId + " does not have task with Id: " + taskId)
            );
        }
        return student;//TODO
    }

    @Transactional
    public Student updateTask(Long userId, Long taskId, Task taskToUpdate) {
        Student student = getStudent(userId);
        boolean isExist = student.updateTaskByTaskId(taskId,taskToUpdate);

        if (!isExist){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Task with Id: " + taskId + " is not belong to Student with Id: " +userId)
            );
        }

        return student;
    }

    public List<Task> getAllTasks(Long userId){
        Student student = getStudent(userId);
        List<Task> tasks = student.getTasks();

        if (tasks.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "Student with With Id: " + userId + " does not have any tasks"
                    ));
        }
        return tasks;

    }
    //Application
    @Transactional
    public Student addApplicationToStudent(Long userId, Application newApplication) {
        Student student = getStudent(userId);
        if (student.getApplications().size() == 2){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    String.format( "Student with Id: " + userId + " has 2 applications. Student cannot have more than 2 application")
            );
        }
        newApplication.setStudent(student);
        student.addApplication(newApplication);
        return student;
    }


    @Transactional
    public Student removeApplicationFromStudent(Long userId, Long applicationId) {
        Student student = getStudent(userId);
        boolean success = student.removeApplicationById(applicationId);

        if(!success){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Application With Id: " + applicationId + " does not exist in Student with Id:" + userId)
            );
        }
        return student;
    }


    @Transactional
    public Student updateApplication(Long userId, Long applicationId, Application updatedApplication) {
        Student student = getStudent(userId);
        boolean success = student.updateApplicationByApplicationId(applicationId, updatedApplication);

        if(!success){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Application With Id: " + applicationId + " does not exist in Student with Id:" + userId)
            );
        }

        return student;
    }


}
