package com.kirillkotov.repository;

import com.kirillkotov.model.Mammal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MammalRepository extends JpaRepository<Mammal, Long> {
}
