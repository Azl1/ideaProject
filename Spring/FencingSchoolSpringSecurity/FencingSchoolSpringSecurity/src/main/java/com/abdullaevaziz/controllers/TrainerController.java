package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Admin;
import com.abdullaevaziz.model.Apprentice;
import com.abdullaevaziz.model.Trainer;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.service.TrainerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private TrainerService trainerService;
    private ObjectMapper objectMapper = new ObjectMapper();
    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    /**
     * • post – осуществляет добавление нового тренера в базу данных
     * (admin)
     */
    /*@PostMapping
    public ResponseEntity<ResponseResult<Trainer>> add(@RequestBody Trainer trainer) {
        try {
            this.trainerService.add(trainer);
            return new ResponseEntity<>(new ResponseResult<>(null, trainer),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }*/
    @PostMapping
    public void add(@RequestBody Trainer trainer, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Trainer trainerNew = this.trainerService.add(trainer);
            response.setStatus(HttpStatus.OK.value());
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Trainer>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Trainer>(null, trainerNew));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Trainer>(e.getMessage(), null));
        }
    }

    /**
     * • get – осуществляет получение тренера по его id
     * (admin, apprentice, a trainer – только себя)
     */
    /*@GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Trainer>> get(@PathVariable long id, Authentication authentication) {
        try {
            Trainer trainer = this.trainerService.get(authentication, id);
            return new ResponseEntity<>(new ResponseResult<Trainer>(null, trainer),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        } catch (IllegalAccessException  e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }*/
    @GetMapping(path = "/{id}")
    public void get(@PathVariable long id, Authentication authentication,  HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        if (authentication != null && authentication.isAuthenticated()) {
            //long id = ((UserDetailsImpl) authentication.getPrincipal()).getId();
            try {
                Trainer trainerGet = trainerService.get(authentication, id);
                this.objectMapper.writerFor(new TypeReference<ResponseResult<Trainer>>() {
                        })
                        .writeValue(response.getOutputStream(),
                                new ResponseResult<Trainer>(null, trainerGet));
            } catch (IllegalArgumentException e) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                this.objectMapper.writeValue(response.getOutputStream(),
                        new ResponseResult<Trainer>(e.getMessage(), null));
            } catch (IllegalAccessException e) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                this.objectMapper.writeValue(response.getOutputStream(),
                        new ResponseResult<Trainer>(e.getMessage(), null));
            }
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Trainer>("Incorrect authentication", null));
        }
    }

    /**
     * • get – осуществляет получение всех тренеров(admin, apprentice)
     */
    /*@GetMapping
    public ResponseEntity<ResponseResult<List<Trainer>>> getAll() {
        List<Trainer> list = this.trainerService.getListTrainer();
        return new ResponseEntity<>(new ResponseResult<>(null, list),
                HttpStatus.OK);
    }*/
    @GetMapping
    public void get(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            List<Trainer> trainersList = this.trainerService.getListTrainer();
            response.setStatus(HttpStatus.OK.value());
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Trainer>>>() {})
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Trainer>>(null, trainersList));
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Trainer>(e.getMessage(), null));
        }
    }

    /**
     *•	• put – осуществляет обновление тренера по его id
     * (admin, а trainer – только себя)
     */
    /*@PutMapping
    public ResponseEntity<ResponseResult<Trainer>> update(@RequestBody Trainer trainer, Authentication authentication) {
        try {
            if (trainer.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>("Incorrect format id", null),
                        HttpStatus.BAD_REQUEST);
            }
            Trainer trainerOld = this.trainerService.update(authentication, trainer);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerOld),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        } catch (IllegalAccessException  e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }*/
    @PutMapping
    public void update(@RequestBody Trainer trainer, Authentication authentication,
                       HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Trainer updatedTrainer = this.trainerService.update(authentication, trainer);
            response.setStatus(HttpStatus.OK.value());
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Trainer>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<>(null, updatedTrainer));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Trainer>(e.getMessage(), null));
        } catch (IllegalAccessException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Trainer>(e.getMessage(), null));
        }
    }

    /**
     * • delete – осуществляет удаление тренера
     * из базы данных по его id (admin, а trainer – только себя)
     */
    /*@DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult<Trainer>> delete(@PathVariable long id, Authentication authentication){
        try {
            Trainer trainerDelete = this.trainerService.delete(authentication, id);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerDelete),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }*/
    @DeleteMapping("/{id}")
    public void delete(@PathVariable  long id, Authentication authentication, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Trainer trainerDelete = this.trainerService.delete(authentication,id);
            response.setStatus(HttpStatus.OK.value());
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Trainer>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Trainer>(null, trainerDelete));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Trainer>(e.getMessage(), null));
        } catch (IllegalAccessException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Trainer>(e.getMessage(), null));
        }
    }
}
