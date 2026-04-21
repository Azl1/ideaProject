package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Compliment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplimentRepository extends JpaRepository<Compliment, Long> {
}
