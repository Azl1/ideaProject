package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Training;
import com.abdullaevaziz.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 5. TrainingController
 */

@RestController
@RequestMapping("/training")
public class TrainingController {

    private TrainingService trainingService;

    @Autowired
    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    /**
     * post – осуществляет добавление новой тренировки для заданного id пользователя и для заданного id тренера
     */
    @PostMapping("/{trainerId}/{apprenticeId}")
    public ResponseEntity<ResponseResult<Training>> add(@PathVariable long trainerId,
                                                        @PathVariable long apprenticeId,
                                                        @RequestBody Training training) {
        try {
            this.trainingService.add(trainerId, apprenticeId, training);
            return new ResponseEntity<>(new ResponseResult<>(null, training), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get – осуществляет получение тренировки по ее id
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Training>> get(@PathVariable long id) {
        try {
            Training training = this.trainingService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, training), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get – осуществляет получение тренировок по id апрентиса
     */
    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<ResponseResult<List<Training>>> getFindByTrainerId(@PathVariable long trainerId) {
        try {
            List<Training> trainerList = this.trainingService.getByTrainerId(trainerId);
            return new ResponseEntity<>(new ResponseResult<List<Training>>(null, trainerList), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get – осуществляет получение тренировок trainerId и dayOfTheWeek
     */
    @GetMapping("/training/{trainerId}")
    public ResponseEntity<ResponseResult<List<Training>>> getTrainerIdAndDateList(@PathVariable long trainerId, @RequestParam LocalDate date) {
        try {
            List<Training> trainerList = this.trainingService.findByTrainerIdAndDate(trainerId, date);
            return new ResponseEntity<>(new ResponseResult<List<Training>>(null, trainerList), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get – осуществляет получение тренировок NumberGym и data
     */
    @GetMapping("/training/{numberGym}")
    public ResponseEntity<ResponseResult<List<Training>>> getNumberGymAndDateList(@PathVariable int numberGym, @RequestParam LocalDate date) {
        try {
            List<Training> trainingList = this.trainingService.findByNumberGymAndDate(numberGym, date);
            return new ResponseEntity<>(new ResponseResult<List<Training>>(null, trainingList), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get – осуществляет получение тренировок Apprentice и data
     */
    @GetMapping("/training/{apprenticeId}")
    public ResponseEntity<ResponseResult<List<Training>>> getApprenticeIdAndDateList(@PathVariable long apprenticeId, @RequestParam LocalDate date) {
        try {
            List<Training> trainingList = this.trainingService.findByApprenticeIdAndDate(apprenticeId, date);
            return new ResponseEntity<>(new ResponseResult<List<Training>>(null, trainingList), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get – осуществляет получение тренировки по ее id тренера
     */
    @GetMapping("/apprentice/{apprenticeId}")
    public ResponseEntity<ResponseResult<List<Training>>> getFindByApprenticeId(@PathVariable long apprenticeId) {
        try {
            List<Training> apprenticeList = this.trainingService.getByApprenticeId(apprenticeId);
            return new ResponseEntity<>(new ResponseResult<List<Training>>(null, apprenticeList), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * delete – удаление тренировки по ее id
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Training>> delete(@PathVariable long id) {
        try {
            Training training = this.trainingService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, training), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
