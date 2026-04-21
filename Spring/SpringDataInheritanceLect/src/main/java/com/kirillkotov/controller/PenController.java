package com.kirillkotov.controller;

import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.Pen;
import com.kirillkotov.service.PenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pen")
public class PenController {
    private PenService penService;

    @Autowired
    public void setPenService(PenService penService) {
        this.penService = penService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Pen>> add(@RequestBody Pen pen) {
        try {
            this.penService.add(pen);
            return new ResponseEntity<>(new ResponseResult<>(null, pen), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Pen>>> get() {
        List<Pen> books = this.penService.getAll();
        return new ResponseEntity<>(new ResponseResult<>(null, books), HttpStatus.OK);
    }
}
