package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.TrainerSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerScheduleRepository extends JpaRepository<TrainerSchedule, Long> {

    Optional<TrainerSchedule> findByTrainerId(long idTrainer);
}
