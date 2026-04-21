package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.TrainerSchedule;
import com.abdullaevaziz.service.TrainerScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/trainerSchedule")
public class TrainerScheduleController {

    private TrainerScheduleService trainerScheduleService;

    @Autowired
    public void setTrainerScheduleService(TrainerScheduleService trainerScheduleService) {
        this.trainerScheduleService = trainerScheduleService;
    }

    /**
     * • post – осуществляет добавление(так же будет работать и на обновление)
     * расписания для конкретного тренера с заданным id,
     * днем недели(подаем на английском языке в виде строки с маленькой буквы),
     * времени начала и конца работы в этот день
     */
    //TODO проверить что этот метод будет работать
    // и на обновление тоже то есть менять его не надо будет
    @PostMapping("/{idTrainer}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> add(@PathVariable long idTrainer,
                                                               @RequestParam String dayOfTheWeek,
                                                               @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime localTimeStart,
                                                               @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime localTimeEnd) {
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.add(idTrainer, dayOfTheWeek, localTimeStart, localTimeEnd);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * • get – осуществляет получение расписания для тренера с заданным id
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> get(@PathVariable long id) {
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * • delete – осуществляет удаление расписания тренера с заданным id и днем
     */
    @DeleteMapping(path = "/{idTrainer}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> delete(@PathVariable long idTrainer, @RequestParam String dayOfTheWeek) {
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.delete(idTrainer, dayOfTheWeek);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
