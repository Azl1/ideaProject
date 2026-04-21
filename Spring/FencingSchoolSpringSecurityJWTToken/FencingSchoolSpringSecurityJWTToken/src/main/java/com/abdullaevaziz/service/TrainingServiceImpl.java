package com.abdullaevaziz.service;

import com.abdullaevaziz.model.*;
import com.abdullaevaziz.repository.TrainingRepository;
import com.abdullaevaziz.repository.UserRepository;
import com.abdullaevaziz.securety.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.abdullaevaziz.util.Util.isOverlapping;

@Service
public class TrainingServiceImpl implements TrainingService {

    private TrainingRepository trainingRepository;
    private TrainerService trainerService;
    private ApprenticeService apprenticeService;
    private TrainerScheduleService trainerScheduleService;
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Autowired
    public void setApprenticeService(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    @Autowired
    public void setTrainerScheduleService(TrainerScheduleService trainerScheduleService) {
        this.trainerScheduleService = trainerScheduleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(long trainerId, long apprenticeId, Training training, Authentication authentication) throws IllegalAccessException {
        try {
            Trainer trainer = this.trainerService.get(authentication, trainerId);
            Apprentice apprentice = this.apprenticeService.get(authentication, apprenticeId);
            TrainerSchedule trainerSchedule = this.trainerScheduleService.get(trainerId, authentication);
            LocalDate date = training.getDate();
            String dateString = date.getDayOfWeek().toString().toLowerCase();

            if (!trainerSchedule.isWorkMan(dateString)) {
                throw new IllegalArgumentException("Тренер не работает в выбранный день");
            }
            LocalTime trainingStart = training.getTimeStart();
            LocalTime trainingEnd = trainingStart.plusMinutes(90);

            List<Training> trainerListDate =
                    this.trainingRepository.findByTrainerIdAndDate(trainerId, date);
            long countDayOfWeek = trainerListDate.stream().filter(x -> {
                LocalTime existingStart = x.getTimeStart();
                LocalTime existingEnd = existingStart.plusMinutes(90);
                return isOverlapping(trainingStart, trainingEnd, existingStart, existingEnd);
            }).count();
            if (countDayOfWeek >= 3) {
                throw new IllegalArgumentException("Тренер уже имеет 3 или более тренировок в это время");
            }
            List<Training> trainingListData =
                    this.trainingRepository.findByNumberGymAndDate(training.getNumberGym(), training.getDate());
            long overlappingGymCount = trainingListData.stream().
                    filter(existing -> {
                        LocalTime existingStart = existing.getTimeStart();
                        LocalTime existingEnd = existingStart.plusMinutes(90);
                        return isOverlapping(trainingStart, trainingEnd, existingStart, existingEnd);
                    }).count();
            if (overlappingGymCount >= 10) {
                throw new IllegalArgumentException
                        ("В зале уже максимальное количество тренировок (10) в это время");
            }
            List<Training> apprenticeListDate =
                    this.trainingRepository.findByApprenticeIdAndDate(apprenticeId, date);
            if (!apprenticeListDate.isEmpty()) {
                throw new IllegalArgumentException
                        ("Ученик в один день не может присутствовать сразу на нескольких тренировках");
            }
            training.setTrainer(trainer);
            training.setApprentice(apprentice);
            this.trainingRepository.save(training);

        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Training has already added!");
        }
    }

    @Override
    public Training get(long id, Authentication authentication) throws IllegalAccessException {
        long userId = ((JwtUser) authentication.getPrincipal()).getId();
        User user = this.userRepository.findById(userId).
                orElseThrow(() -> new IllegalArgumentException("User not found"));
        Training training = this.trainingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Training does not exists!"));
        if (user instanceof Admin) {
            return training;
        }
        else if (user instanceof Trainer) {
                return training;
        }
        else if (user instanceof Apprentice) {
            if (training.getApprentice() != null && training.getApprentice().getId() == user.getId()) {
                return training;
            }
            throw new IllegalAccessException("Ученик может просматривать только свои тренировки");
        }
        throw new IllegalAccessException("Неизвестный тип пользователя");

    }

    @Override
    public Training get(long trainerId, long apprenticeId, Authentication authentication) throws IllegalAccessException {
        long userId = ((JwtUser) authentication.getPrincipal()).getId();
        User user = this.userRepository.findById(userId).
                orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user.getClass() != Trainer.class && user.getClass() != Admin.class && trainerId != userId) {
            throw new IllegalAccessException("Apprentice does not match user id authentication");
        }
        return this.trainingRepository.findByTrainerIdAndApprenticeId(trainerId, apprenticeId)
                .orElseThrow(() -> new IllegalArgumentException("Training does not userId and trainerId exists!"));
    }



    @Override
    public List<Training> getByTrainerId(long trainerId, Authentication authentication) throws IllegalAccessException {
        Trainer trainer = this.trainerService.get(authentication, trainerId);
        return this.trainingRepository.findByTrainerId(trainer.getId());
    }

    @Override
    public List<Training> getByApprenticeId(long apprenticeId, Authentication authentication) throws IllegalAccessException {
        Apprentice apprentice = this.apprenticeService.get(authentication, apprenticeId);
        return this.trainingRepository.findByApprenticeId(apprentice.getId());
    }

    @Override
    public Training delete(long id, Authentication authentication) throws IllegalAccessException {
        Training training = this.get(id, authentication);
        this.trainingRepository.delete(training);
        return training;
    }

    @Override
    public List<Training> findByTrainerIdAndDate(long trainerId,
                                                 LocalDate date,
                                                 Authentication authentication) {
        return trainingRepository.findByTrainerIdAndDate(trainerId, date);
    }

    @Override
    public List<Training> findByNumberGymAndDate(int numberGym,
                                                 LocalDate date,
                                                 Authentication authentication) {
        return trainingRepository.findByNumberGymAndDate(numberGym, date);
    }

    @Override
    public List<Training> findByApprenticeIdAndDate(long apprenticeId,
                                                    LocalDate date,
                                                    Authentication authentication) {
        return trainingRepository.findByApprenticeIdAndDate(apprenticeId, date);
    }
}
