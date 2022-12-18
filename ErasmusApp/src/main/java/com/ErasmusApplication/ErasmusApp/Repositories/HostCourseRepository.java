package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.HostCourse;
import com.ErasmusApplication.ErasmusApp.Models.HostUniversity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HostCourseRepository extends JpaRepository<HostCourse, Long> {


    @Query("select (count(h) > 0) from HostCourse h where h.nameOfCourse = ?1")
    boolean existsByNameOfCourse(String nameOfCourse);

    @Query("""
            select (count(h) > 0) from HostCourse h
            where h.hostUniversity.nameOfInstitution = ?1 and h.nameOfCourse = ?2""")
    boolean existsByHostUniversity_NameOfInstitutionAndNameOfCourse(String nameOfInstitution, String nameOfCourse);


    Optional<HostCourse> findByNameOfCourse(String nameOfCourse);



}
