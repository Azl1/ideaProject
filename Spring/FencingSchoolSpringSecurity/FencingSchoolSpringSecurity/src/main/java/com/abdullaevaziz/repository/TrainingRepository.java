package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    Optional<Training> findByTrainerIdAndApprenticeId(long trainerId, long apprenticeId);
    List<Training> findByTrainerId(long trainerId);
    List<Training> findByApprenticeId(long apprenticeId);
    List<Training> findByTrainerIdAndDate(long trainerId, LocalDate date);
    List<Training> findByNumberGymAndDate(int numberGym, LocalDate date);
    List<Training> findByApprenticeIdAndDate(long apprenticeId, LocalDate date);
}
