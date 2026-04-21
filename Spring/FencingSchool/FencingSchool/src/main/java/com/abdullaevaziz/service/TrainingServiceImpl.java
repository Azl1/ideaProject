package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Apprentice;
import com.abdullaevaziz.model.Trainer;
import com.abdullaevaziz.model.TrainerSchedule;
import com.abdullaevaziz.model.Training;
import com.abdullaevaziz.repository.ApprenticeRepository;
import com.abdullaevaziz.repository.TrainerRepository;
import com.abdullaevaziz.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.abdullaevaziz.util.Util.isOverlapping;

@Service
public class TrainingServiceImpl implements TrainingService {

    private TrainingRepository trainingRepository;
    private UserService userService;
    private TrainerService trainerService;
    private ApprenticeService apprenticeService;
    private TrainerScheduleService trainerScheduleService;
    private ApprenticeRepository apprenticeRepository;
    private TrainingService trainingService;

    @Autowired
    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @Autowired
    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
    public void setApprenticeRepository(ApprenticeRepository apprenticeRepository) {
        this.apprenticeRepository = apprenticeRepository;
    }

    private TrainerRepository trainerRepository;

    @Autowired
    public void setTrainerRepository(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    /**
     * При добавлении новой тренировки для тренера, необходимо учитывать,
     * что тренер одновременно не может принимать более 3 учеников,
     * а так же не ведет прием в нерабочее время.
     * Одновременно в зале могут заниматься только 10 учеников.
     * Ученик в один день не может присутствовать сразу на нескольких тренировках.
     */
    @Override
    public void add(long trainerId, long apprenticeId, Training training) {
        Trainer trainer = this.trainerService.get(trainerId);
        Apprentice apprentice = this.apprenticeService.get(apprenticeId);
        TrainerSchedule trainerSchedule = this.trainerScheduleService.get(trainerId);
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
        long overlappingGymCount = trainingListData.stream()
                .filter(existing -> {
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
        try {
            this.trainingRepository.save(training);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Training has already added!");
        }
    }

    @Override
    public Training get(long id) {
        return this.trainingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Training does not exists!"));
    }

    @Override
    public Training get(long trainerId, long apprenticeId) {
        return this.trainingRepository.findByTrainerIdAndApprenticeId(trainerId, apprenticeId)
                .orElseThrow(() -> new IllegalArgumentException("Training does not userId and trainerId exists!"));
    }

    @Override
    public List<Training> getByTrainerId(long trainerId) {
        //TODO получить тренера из его сервса по айди trainerId
        Trainer trainer = this.trainerService.get(trainerId);
        //TODO в репозитории тренировок написать метод который вернет список тренироввок по айди трена
        return this.trainingRepository.findByTrainerId(trainer.getId());
    }

    //TODO аналогично исправить для апрентиса
    @Override
    public List<Training> getByApprenticeId(long apprenticeId) {
        Apprentice apprentice = this.apprenticeService.get(apprenticeId);
        return this.trainingRepository.findByApprenticeId(apprentice.getId());
    }

    @Override
    public Training delete(long id) {
        Training training = this.get(id);
        this.trainingRepository.deleteById(id);
        return training;
    }

    @Override
    public List<Training> findByTrainerIdAndDate(long trainerId, LocalDate date) {
        return trainingRepository.findByTrainerIdAndDate(trainerId, date);
    }

    @Override
    public List<Training> findByNumberGymAndDate(int numberGym, LocalDate date) {
        return trainingRepository.findByNumberGymAndDate(numberGym, date);
    }

    @Override
    public List<Training> findByApprenticeIdAndDate(long apprenticeId, LocalDate date) {
        return trainingRepository.findByApprenticeIdAndDate(apprenticeId, date);
    }
}
