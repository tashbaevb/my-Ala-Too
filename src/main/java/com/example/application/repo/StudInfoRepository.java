package com.example.application.repo;

import com.example.application.entity.StudInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudInfoRepository extends JpaRepository<StudInfo, Integer> {
}
