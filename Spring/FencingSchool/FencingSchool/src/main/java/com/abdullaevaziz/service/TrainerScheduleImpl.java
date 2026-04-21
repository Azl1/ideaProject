package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Trainer;
import com.abdullaevaziz.model.TrainerSchedule;
import com.abdullaevaziz.repository.TrainerScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class TrainerScheduleImpl implements TrainerScheduleService {

    private TrainerScheduleRepository trainerScheduleRepository;
    private TrainerService trainerService;
    private ApprenticeService apprenticeService;

    @Autowired
    public void setTrainerScheduleImpl(TrainerScheduleRepository trainerScheduleRepository) {
        this.trainerScheduleRepository = trainerScheduleRepository;
    }

    @Autowired
    public void setTrainerScheduleImpl(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Autowired
    public void setTrainerScheduleImpl(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    /**
     * • post – осуществляет добавление(так же будет работать и на обновление)
     * расписания для конкретного тренера с заданным id,
     * днем недели(подаем на английском языке в виде строки с маленькой буквы),
     * времени начала и конца работы в этот день
     */
    @Override
    public TrainerSchedule add(long idTrainer, String dayOfTheWeek,
                               LocalTime localTimeStart, LocalTime localTimeEnd) {

        Trainer trainer = this.trainerService.get(idTrainer);

        TrainerSchedule trainerSchedule = this.trainerScheduleRepository.
                findByTrainerId(trainer.getId()).orElse(new TrainerSchedule(trainer));
        String normalizedDay = dayOfTheWeek.toLowerCase();
        trainerSchedule.set(normalizedDay, localTimeStart, localTimeEnd);

        //TODO добавить объект trainerSchedule в базу
        try {
            this.trainerScheduleRepository.save(trainerSchedule);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("TrainerSchedule has already added!");
        }
        return trainerSchedule;
    }


    /**
     * • get – осуществляет получение расписания для тренера с заданным id
     */
    @Override
    public TrainerSchedule get(long id) {
        Trainer trainer = this.trainerService.get(id);
        return this.trainerScheduleRepository.findById(id).
                orElse(new TrainerSchedule(trainer));

        //TODO потом сделать тест когда айди корректный

        //TODO когдла нет такого тренера с таким айди  а

        //TODO когда тренер есть но раписания у него нет
    }

    /**
     * • delete – осуществляет удаление расписания тренера с заданным id
     * и днем недели(подаем на английском языке в виде строки с маленькой буквы)
     */
    @Override
    public TrainerSchedule delete(long idTrainer, String dayOfTheWeek) {
        Trainer trainer = this.trainerService.get(idTrainer);
        TrainerSchedule trainerSchedule = this.trainerScheduleRepository.
                findByTrainerId(trainer.getId()).
                orElseThrow(() -> new IllegalArgumentException("TrainerSchedule not found!"));
        String normalizedDay = dayOfTheWeek.toLowerCase();
        trainerSchedule.set(normalizedDay, null, null);

        this.trainerScheduleRepository.deleteById(trainer.getId());

        return trainerSchedule;
    }
}
