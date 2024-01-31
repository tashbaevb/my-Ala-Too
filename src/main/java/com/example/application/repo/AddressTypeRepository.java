package com.example.application.repo;

import com.example.application.entity.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressTypeRepository extends JpaRepository<AddressType, Integer> {
}
