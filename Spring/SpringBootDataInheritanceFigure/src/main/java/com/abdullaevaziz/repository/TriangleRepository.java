package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Triangle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriangleRepository extends JpaRepository<Triangle, Long> {
}
