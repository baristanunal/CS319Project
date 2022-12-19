//package com.ErasmusApplication.ErasmusApp.Controllers;
//
//import com.ErasmusApplication.ErasmusApp.Models.Application;
//import com.ErasmusApplication.ErasmusApp.Models.Student;
//import com.ErasmusApplication.ErasmusApp.Models.Task;
//
//import com.ErasmusApplication.ErasmusApp.Services.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.util.List;
//
//@RestController
//@RequestMapping("/student")
//public class StudentController {
//    private final StudentService studentService;
//
//    @Autowired
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Student>> getStudents(){
//        return ResponseEntity.ok().body(studentService.getStudents());
//    }
//
//    @PostMapping("/save")
//    public void saveStudent(@RequestBody Student student){
//      studentService.saveStudent(student);
//    }
//
//    @DeleteMapping("{userId}")
//    public void deleteStudent(@PathVariable Long userId){
//        studentService.deleteStudent(userId);
//    }
//
//    @PutMapping("{userId}") // Try to delete this and combine this with just @RequestBody,  I tried but it gives the error : Content-Type '*/*;charset=UTF-8' is not supported
//    public void updateStudent( @PathVariable("userId") Long userId, @RequestBody Student student){
//        studentService.updateStudent(userId, student);
//    }
//
//    //TASKS
//    //In usage
//    @GetMapping("{userId}/getAllTasks")
//    public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long userId){
//            return new ResponseEntity<>(studentService.getAllTasks(userId), HttpStatus.OK);
//    }
//
//    @PostMapping("{userId}/tasks/add")
//    public ResponseEntity<Student> addTaskToStudent(@PathVariable Long userId, @RequestBody Task newTask){
//        Student student = studentService.addTaskToStudent(userId,newTask);
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }
//    // IN usage
//    @PostMapping("{userId}/tasks/remove/{taskId}")
//    public ResponseEntity<Student> removeTaskFromStudent(@PathVariable Long userId, @PathVariable Long taskId){
//        Student student = studentService.removeTaskFromStudent(userId, taskId);
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }
//    // IN usage
//    @PutMapping("{userId}/tasks/update/{taskId}")
//    public ResponseEntity<Student> updateTask(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody Task taskToUpdate){
//        Student student = studentService.updateTask(userId, taskId, taskToUpdate);
//
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }
//
//
//
//    //Application
//    @PostMapping("{userId}/application/add")
//    public ResponseEntity<Student> addApplicationToStudent(@PathVariable Long userId, @RequestBody Application newApplication){
//        Student student = studentService.addApplicationToStudent(userId,newApplication);
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }
//
//
//    //TODO type of return
//    @PostMapping("{userId}/application/remove/{applicationId}")
//    public Student removeApplication(@PathVariable Long userId, @PathVariable Long applicationId){
//        return studentService.removeApplicationFromStudent(userId,applicationId);
//    }
//
//
//    //TODO type of return
//    @PostMapping("{userId}/application/update/{applicationId}")
//    public Student updateApplicationByApplicationId(@PathVariable Long userId, @PathVariable Long applicationId, @RequestBody Application updatedApplication){
//        return studentService.updateApplicationByApplicationId(userId, applicationId, updatedApplication);
//    }
//
//    //TODO type of return
//    @PostMapping("{userId}/application/update/byType/{appTypeNo}")
//    public Student updateApplicationByApplicationType(@PathVariable Long userId, @PathVariable int appTypeNo, @RequestBody Application updatedApplication){
//        String applicationType = "Exchange";
//        if (appTypeNo == 0){
//            applicationType = "Erasmus";
//        }
//        return studentService.updateApplicationByApplicationType(userId, applicationType, updatedApplication);
//    }
//}
