package com.kirillkotov.controller;

import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.Dog;
import com.kirillkotov.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {
    private DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Dog>>> get() {
        List<Dog> dogs = this.dogService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, dogs), HttpStatus.OK);
    }

}
