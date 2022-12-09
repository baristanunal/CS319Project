package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Models.Task;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Repositories.StudentRepository;
import com.ErasmusApplication.ErasmusApp.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable Long userId){
        studentService.deleteUser(userId);
    }

    @PutMapping("{userId}") // Try to delete this and combine this with just @RequestBody,  I tried but it gives the error : Content-Type '*/*;charset=UTF-8' is not supported
    public void updateStudent( @PathVariable("userId") Long userId, @RequestBody Student student){
        studentService.updateStudent(userId, student);
    }

    @PostMapping("{userId}/tasks/add")
    public ResponseEntity<Student> addTaskToStudent(@PathVariable Long userId, @RequestBody Task newTask){
        Student student = studentService.addTaskToStudent(userId,newTask);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @PostMapping("{userId}/tasks/remove/{taskId}")
    public ResponseEntity<Student> removeTaskFromStudent(@PathVariable Long userId, @PathVariable Long taskId){
        Student student = studentService.removeTaskFromStudent(userId, taskId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @PutMapping("{userId}/tasks/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody Task taskToUpdate){
        Boolean success = studentService.updateTask(userId, taskId, taskToUpdate);

        if (success){
            return new ResponseEntity<>(taskToUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<>(taskToUpdate, HttpStatus.BAD_REQUEST); //TODO does not work
    }


    @PostMapping("{userId}/application/add")
    public ResponseEntity<Student> addApplicationToStudent(@PathVariable Long userId, @RequestBody Application newApplication){
        Student student = studentService.addApplicationToStudent(userId,newApplication);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

//    @PutMapping(path = "{userId}")
//    public void updateStudent(
//            @PathVariable("userId") Long userId,
//            @RequestParam(required = false) String email,
//            @RequestParam(required = false) String firstName,
//            @RequestParam(required = false) String lastName,
//            @RequestParam(required = false) long schoolId,
//            @RequestParam(required = false) String department,
//            @RequestParam(required = false) String academicYear,
//            @RequestParam(required = false) String birthDate,
//            @RequestParam(required = false) String nationality,
//            @RequestParam(required = false) String gender,
//        ){
//
//    }
}