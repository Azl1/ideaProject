package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Trainer;
import com.abdullaevaziz.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private TrainerService trainerService;

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    /**
     * post – осуществляет добавление нового тренера в базу данных
     */
    @PostMapping
    public ResponseEntity<ResponseResult<Trainer>> add(@RequestBody Trainer trainer) {
        try {
            this.trainerService.add(trainer);
            return new ResponseEntity<>(new ResponseResult<>(null, trainer), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get – осуществляет получение всех тренеров
     */
    @GetMapping
    public ResponseEntity<ResponseResult<List<Trainer>>> get() {
        List<Trainer> list = this.trainerService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, list), HttpStatus.OK);
    }

    /**
     * get – осуществляет получение тренера по его id
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Trainer>> get(@PathVariable long id) {
        try {
            Trainer trainer = this.trainerService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, trainer), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * delete – осуществляет удаление тренера из базы данных по его id
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Trainer>> delete(@PathVariable long id) {
        try {
            Trainer trainer = this.trainerService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, trainer), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * put – осуществляет обновление тренера по его id
     */
    @PutMapping
    public ResponseEntity<ResponseResult<Trainer>> update(@RequestBody Trainer trainer) {
        try {
            if (trainer.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>("Incorrect format id", null),
                        HttpStatus.BAD_REQUEST);
            }
            Trainer trainerOld = this.trainerService.update(trainer);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerOld), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
