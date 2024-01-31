package com.example.application.repo;

import com.example.application.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findByTitleKgOrTitleRuOrTitleEn(String kg,String ru,String en);
    Optional<Address> findByTitleEn(String en);
}
