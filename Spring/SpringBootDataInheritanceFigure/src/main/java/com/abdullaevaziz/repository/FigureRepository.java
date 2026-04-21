package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Figure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FigureRepository extends JpaRepository<Figure, Long> {
}
