package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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