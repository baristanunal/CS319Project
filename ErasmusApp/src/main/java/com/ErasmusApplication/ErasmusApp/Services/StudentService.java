package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Models.Task;
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
    public Student getStudent(Long userId){
        return studentRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student With Id: " + userId + " does not exist."
                ));
    }

    public Student addNewStudent(Student student) {
        Optional<UserClass> userBySchoolId = studentRepository.findBySchoolId(student.getSchoolId());
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

    @Transactional
    public Student addTaskToStudent(Long userId, Task newTask) {
        Student student = getStudent(userId);
        newTask.setUser(student);
        student.addTask(newTask);

        //TODO delete this part, I am not sure which way is the best but both of them works fine
//        Student student = getStudent(userId);
//        Task task = taskRepository.save(newTask);
//        newTask.setUser(student);
//        student.addTask(task);


        return student;//TODO
    }


    //TODO  move the remove part to TaskService  and handle the other operations there, CRUD
    @Transactional
    public Student removeTaskFromStudent(Long userId, Long taskId) {
        Student student = getStudent(userId);
        student.removeTaskById(taskId);

        return student;//TODO
    }

    @Transactional
    public Student addApplicationToStudent(Long userId, Application newApplication) {
        Student student = getStudent(userId);
        newApplication.setStudent(student);
        student.addApplication(newApplication);
        return student;
    }
}
