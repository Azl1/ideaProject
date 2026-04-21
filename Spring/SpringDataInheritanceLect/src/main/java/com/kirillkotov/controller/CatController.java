package com.kirillkotov.controller;

import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.Cat;
import com.kirillkotov.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {
    private CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Cat>>> get() {
        List<Cat> cats = this.catService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, cats), HttpStatus.OK);
    }


}
