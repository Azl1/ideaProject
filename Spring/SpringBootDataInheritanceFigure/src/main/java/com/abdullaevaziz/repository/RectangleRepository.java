package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Rectangle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RectangleRepository extends JpaRepository<Rectangle, Long> {
}
