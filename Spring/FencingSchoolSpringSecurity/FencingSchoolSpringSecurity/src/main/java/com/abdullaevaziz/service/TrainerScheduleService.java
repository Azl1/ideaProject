package com.abdullaevaziz.service;

import com.abdullaevaziz.model.TrainerSchedule;
import org.springframework.security.core.Authentication;

import java.time.LocalTime;

public interface TrainerScheduleService {

    TrainerSchedule add(long idTrainer, String dayOfTheWeek,
                        LocalTime localTimeStart, LocalTime localTimeEnd, Authentication authentication) throws IllegalAccessException;

    TrainerSchedule get(long id, Authentication authentication) throws IllegalAccessException;

    TrainerSchedule delete(long id, String dayOfTheWeek, Authentication authentication) throws IllegalAccessException;
}
