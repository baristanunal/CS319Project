package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import com.ErasmusApplication.ErasmusApp.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("select a from Application a where a.student.id = ?1")
    List<Application> findByStudent_Id(Long Id);

    @Query("select a from Application a where a.student.id = ?1 and a.id = ?2")
    Optional<Application> findByStudent_IdAndId(Long Id, Long id);









}
