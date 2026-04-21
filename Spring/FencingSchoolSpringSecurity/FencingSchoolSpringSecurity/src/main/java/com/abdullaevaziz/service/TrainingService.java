package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Training;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

public interface TrainingService {

    void add(long trainerId, long apprenticeId, Training training, Authentication authentication) throws IllegalAccessException;
    Training get(long id, Authentication authentication) throws IllegalAccessException;
    Training get(long trainerId, long apprenticeId, Authentication authentication) throws IllegalAccessException;
    List<Training> getByTrainerId(long trainerId, Authentication authentication) throws IllegalAccessException;
    List<Training> getByApprenticeId(long apprenticeId, Authentication authentication) throws IllegalAccessException;
    Training delete(long id, Authentication authentication) throws IllegalAccessException;
    List<Training> findByTrainerIdAndDate(long trainerId, LocalDate date, Authentication authentication);
    List<Training> findByNumberGymAndDate(int numberGym, LocalDate date, Authentication authentication);
    List<Training> findByApprenticeIdAndDate(long apprenticeId, LocalDate date, Authentication authentication);

}
