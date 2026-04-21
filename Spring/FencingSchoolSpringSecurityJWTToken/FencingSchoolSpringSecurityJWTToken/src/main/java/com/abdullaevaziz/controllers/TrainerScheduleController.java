package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.TrainerSchedule;
import com.abdullaevaziz.securety.jwt.JwtTokenProvider;
import com.abdullaevaziz.service.TrainerScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/trainerSchedule")
public class TrainerScheduleController {

    private TrainerScheduleService trainerScheduleService;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public TrainerScheduleController(TrainerScheduleService trainerScheduleService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider){
        this.trainerScheduleService = trainerScheduleService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /*@Autowired
    public void setTrainerScheduleService(TrainerScheduleService trainerScheduleService){
        this.trainerScheduleService = trainerScheduleService;
    }*/

    /**
     * • post – осуществляет добавление(так же будет работать и на обновление)
     * расписания для конкретного тренера с заданным id,
     * днем недели(подаем на английском языке в виде строки с маленькой буквы),
     * времени начала и конца работы в этот день (admin, а trainer – только себя)
     */
    @PostMapping("/{idTrainer}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> add(@PathVariable long idTrainer,
                                                               @RequestParam String dayOfTheWeek,
                                                               @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime localTimeStart,
                                                               @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime localTimeEnd,
                                                               Authentication authentication) {
        try {
            TrainerSchedule trainerSchedule =
                    this.trainerScheduleService.add(idTrainer, dayOfTheWeek, localTimeStart, localTimeEnd, authentication);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        } catch (IllegalAccessException  e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }

    /**
     * • get – осуществляет получение расписания для тренера
     * с заданным id (admin, apprentice, а trainer – только себя)
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> get(@PathVariable long id, Authentication authentication) {
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.get(id, authentication);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        } catch (IllegalAccessException  e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }

    /**
     * • delete – осуществляет удаление расписания тренера
     * с заданным id и днем недели(подаем на английском языке
     * в виде строки с маленькой буквы). При удалении расписания
     * необходимо удалить все тренировки в дни расписания.
     * (admin, а trainer – только себя)
     */
    @DeleteMapping(path = "/{idTrainer}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> delete(@PathVariable long idTrainer,
                                                                  @RequestParam String dayOfTheWeek,
                                                                  Authentication authentication) {
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.delete(idTrainer, dayOfTheWeek, authentication);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        } catch (IllegalAccessException  e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }

}
