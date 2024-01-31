package com.example.application.repo;

import com.example.application.entity.Military;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilitaryRepository extends JpaRepository<Military, Integer> {
}
