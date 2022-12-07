package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.Task;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface TaskRepository extends JpaRepository<Task, Long> {
//    List<User> findByUserId(Long userId);
//
//    @Transactional
//    void deleteByUserId(Long userId);
}