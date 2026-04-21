package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Training;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface TrainingService {

    void add(long trainerId, long apprenticeId, Training training);
    Training get(long id);
    Training get(long trainerId, long apprenticeId);
    List<Training> getByTrainerId(long trainerId);
    List<Training> getByApprenticeId(long apprenticeId);
    Training delete(long id);
    List<Training> findByTrainerIdAndDate(long trainerId, LocalDate date);
    List<Training> findByNumberGymAndDate(int numberGym, LocalDate date);
    List<Training> findByApprenticeIdAndDate(long apprenticeId, LocalDate date);

}
