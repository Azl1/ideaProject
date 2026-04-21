package com.kirillkotov.controllers;

import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.TV;
import com.kirillkotov.service.TVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tv")
public class TVController {
    private TVService tvService;

    @Autowired
    public void setTvService(TVService tvService) {
        this.tvService = tvService;
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity<ResponseResult<TV>> add(@PathVariable long userId, @RequestBody TV tv) {
        try {
            this.tvService.add(userId, tv);
            return new ResponseEntity<>(new ResponseResult<>(null, tv), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<TV>>> get() {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.tvService.get()), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<TV>> get(@PathVariable long id) {
        try {
            TV tv = this.tvService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, tv), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<TV>> update(@RequestBody TV tv) {
        try {
            TV res = this.tvService.update(tv);
            return new ResponseEntity<>(new ResponseResult<>(null, res), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<TV>> delete(@PathVariable long id) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.tvService.delete(id)),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
