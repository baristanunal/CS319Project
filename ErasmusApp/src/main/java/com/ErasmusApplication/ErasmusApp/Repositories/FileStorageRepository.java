//package com.ErasmusApplication.ErasmusApp.Repositories;
//
//import com.ErasmusApplication.ErasmusApp.Models.Student;
//import com.ErasmusApplication.ErasmusApp.Properties.FileStorageProperties;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.Optional;
//
//public interface FileStorageRepository extends JpaRepository<FileStorageProperties, Long> {
//    @Query("select s from Student s where s.schoolId = ?1")
//    Optional<Student> findBySchoolIdOpt(String schoolId);
//
//    Student findBySchoolId(String schoolId);
//
//}
