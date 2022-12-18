package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.HostUniversity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface HostUniversityRepository extends JpaRepository<HostUniversity, Long> {
    Optional<HostUniversity> findByNameOfInstitution(String nameOfInstitution);


}
