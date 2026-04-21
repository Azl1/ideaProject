package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Card;
import com.abdullaevaziz.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<List<Card>> findByCategoryId(long categoryId);
}
