package com.example.application.repo;

import com.example.application.entity.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Integer> {
    Optional<MaritalStatus> findByTitleEn(String gender);
}
