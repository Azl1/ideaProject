package com.abdullaevaziz.service;

import com.abdullaevaziz.model.*;
import com.abdullaevaziz.repository.TrainerScheduleRepository;
import com.abdullaevaziz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class TrainerScheduleServiceImpl implements TrainerScheduleService{

    private TrainerScheduleRepository trainerScheduleRepository;
    private TrainerService trainerService;
    private ApprenticeService apprenticeService;
    private UserRepository userRepository;

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

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public TrainerSchedule add(long idTrainer, String dayOfTheWeek,
                               LocalTime localTimeStart,
                               LocalTime localTimeEnd,
                               Authentication authentication) throws IllegalAccessException {

        /*long userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        User user = this.userRepository.findById(userId).
                orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(user.getClass() != Apprentice.class && user.getClass() != Admin.class && idTrainer != userId){
            throw new IllegalAccessException("Apprentice does not match user id authentication");
        }*/

        Trainer trainer = this.trainerService.get(authentication, idTrainer);
        TrainerSchedule trainerSchedule = this.trainerScheduleRepository.
                findByTrainerId(trainer.getId()).orElse(new TrainerSchedule(trainer));
        String normalizeDay = dayOfTheWeek.toLowerCase();
        trainerSchedule.set(normalizeDay, localTimeStart, localTimeEnd);

        try {
            this.trainerScheduleRepository.save(trainerSchedule);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("TrainerSchedule has already added!");
        }
        return trainerSchedule;
    }

    @Override
    public TrainerSchedule get(long id, Authentication authentication) throws IllegalAccessException {
        Trainer trainer = this.trainerService.get(authentication, id);
        return this.trainerScheduleRepository.findById(id).
                orElse(new TrainerSchedule(trainer));
    }

    @Override
    public TrainerSchedule delete(long idTrainer, String dayOfTheWeek, Authentication authentication) throws IllegalAccessException {
        Trainer trainer = this.trainerService.get(authentication, idTrainer);
        TrainerSchedule trainerSchedule = this.trainerScheduleRepository.findByTrainerId(trainer.getId()).
                        orElseThrow(()-> new IllegalArgumentException("TrainerSchedule not found!"));
        String normalizeDay = dayOfTheWeek.toLowerCase();
        trainerSchedule.set(normalizeDay, null, null);
        this.trainerScheduleRepository.save(trainerSchedule);
        return trainerSchedule;
    }
}
