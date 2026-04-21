package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Compliment;
import com.abdullaevaziz.service.ComplimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/compliments")
public class ComplimentController {

    private ComplimentService complimentService;

    @Autowired
    public void setComplimentService(ComplimentService complimentService) {
        this.complimentService = complimentService;
    }

    /**
     * Позволяет получить все комплименты
     */
    @GetMapping
    public ResponseEntity<ResponseResult<List<Compliment>>> getAll() {
        try {
            List<Compliment> list = this.complimentService.getList();
            System.out.println(list);
            return new ResponseEntity<>(new ResponseResult<>(null, list),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Комплименты по id
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Compliment>> get(@PathVariable long id, Authentication authentication) {
        try {
            Compliment compliment = this.complimentService.getById(authentication, id);
            return new ResponseEntity<>(new ResponseResult<Compliment>(null, compliment),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }


    /**
     * Получить следующий рандомный комплимент
     */
    @GetMapping("/random")
    public ResponseEntity<ResponseResult<Compliment>> getRandom(Authentication authentication) {
        try {
            Compliment complimentRandom = this.complimentService.getComplimentRandom(authentication);
            return new ResponseEntity<>(new ResponseResult<>(null, complimentRandom),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }

    }
}
