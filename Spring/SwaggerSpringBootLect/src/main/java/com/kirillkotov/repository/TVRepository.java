package com.kirillkotov.repository;

import com.kirillkotov.model.TV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface TVRepository extends JpaRepository<TV, Long> {
    Optional<TV> findByBrandAndModel(String brand, String model); //на возврат либо List, либо Optional, либо объект
}
