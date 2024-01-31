package com.example.application.repo;

import com.example.application.entity.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Integer> {
}
