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

    @PostMapping
    public ResponseEntity<ResponseResult<TV>> add(@RequestBody TV tv) {
        try {
            this.tvService.add(tv);
            return new ResponseEntity<>(new ResponseResult<>(null, tv), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<TV>>> get() {
        List<TV> list = this.tvService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, list), HttpStatus.OK);
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

    @GetMapping("/search")
    public ResponseEntity<ResponseResult<List<TV>>> get(@RequestParam String brand) {
        List<TV> tv = this.tvService.get(brand);
        return new ResponseEntity<>(new ResponseResult<>(null, tv), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<TV>> delete(@PathVariable long id) {
        try {
            TV tv = this.tvService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, tv), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<TV>> update(@RequestBody TV tv) {
        try {
            if (tv.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>("Incorrect format id", null),
                        HttpStatus.BAD_REQUEST);
            }
            TV old = this.tvService.update(tv);
            return new ResponseEntity<>(new ResponseResult<>(null, old), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
