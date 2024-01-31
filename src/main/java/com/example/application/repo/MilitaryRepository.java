package com.example.application.repo;

import com.example.application.entity.Military;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MilitaryRepository extends JpaRepository<Military, Integer> {
    Optional<Military> findByTitleEn(String gender);
}
