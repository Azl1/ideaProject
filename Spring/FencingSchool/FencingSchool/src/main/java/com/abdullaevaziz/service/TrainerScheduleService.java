package com.abdullaevaziz.service;

import com.abdullaevaziz.model.TrainerSchedule;

import java.time.LocalTime;

public interface TrainerScheduleService {

    TrainerSchedule add(long idTrainer, String dayOfTheWeek,
                        LocalTime localTimeStart, LocalTime localTimeEnd);

    TrainerSchedule get(long id);

    TrainerSchedule delete(long id, String dayOfTheWeek);
}
