package com.example.application.repo;

import com.example.application.entity.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Integer> {
    Optional<Nationality> findByTitleEn(String en);
}
