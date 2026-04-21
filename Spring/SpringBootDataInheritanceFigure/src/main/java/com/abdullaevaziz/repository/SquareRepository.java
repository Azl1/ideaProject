package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Square;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquareRepository extends JpaRepository<Square, Long> {
}
