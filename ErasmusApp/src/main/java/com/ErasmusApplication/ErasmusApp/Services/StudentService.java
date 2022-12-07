package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
//    ErasmusManager erasmusManager;
//    LoginManager loginManager;
    //TODO  add erasmus manager with its implementation

    StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);
//        Optional<UserClass> userBySchoolId = studentRepository.findBySchoolId(student.getSchoolId());
//        if( userBySchoolId.isPresent()){
//            throw new IllegalStateException("School Id is taken!");
//        }
//        studentRepository.save(student);
    }

    public void deleteUser(Long userId) {
        studentRepository.deleteById(userId);
    }

    @Transactional
    public void updateStudent(Long userId,Student updatedStudent){
        Student student = studentRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student With Id: " + updatedStudent.getId() + " does not exist."
                ));


        student.setDepartment("asd");
        student.setEmail("q@@@");
        // look  https://youtu.be/9SGDpanrc8U?t=5047
        // and add exception,corner case and
        // also if request come for just 1 attribute, we assume other attributes are same with current student,
        // If somehow frontend sends these data with empty string ""  and state that do not update the attibutes with "" or something like that then we need to update this code
        // but in that situation we restricted to that  we cannot set "" any attribute but may be we want that.

        //Code below does not work, we need to use .set for each attribute if
//        student = updatedStudent; //TODO try to write deepcopy in short way or in class. That is, do smth like student.setMail(updatedStudent.getMail() ); for all attributes
//        student.setId(userId); //TODO also dele this after writing deepcopy

//        Student student = studentRepository.findById(updatedStudent.getId()).get();  // This also works but idk the difference, I will look this later

    }
}
