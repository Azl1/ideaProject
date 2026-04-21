package com.kirillkotov.repository;

import com.kirillkotov.model.TV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVRepository extends JpaRepository<TV, Long> {
}
