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
@RequestMapping("/tvs")
public class TVController {
    private TVService tvService;

    @Autowired
    public void setTvService(TVService tvService) {
        this.tvService = tvService;
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity<ResponseResult<TV>> add(@PathVariable String userId,
                                                  @RequestBody TV tv) {
        try {
            this.tvService.add(userId, tv);
            return new ResponseEntity<>(new ResponseResult<>(null, tv), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<TV>>> getAll() {
        List<TV> tvs = this.tvService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, tvs), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<TV>> getById(@PathVariable String id) {
        try {
            TV tv = this.tvService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, tv), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<TV>> delete(@PathVariable String id) {
        try {
            TV delete = this.tvService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, delete), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<TV>> update(@RequestBody TV tv) {
        try {
            TV update = this.tvService.update(tv);
            return new ResponseEntity<>(new ResponseResult<>(null, update), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updatePrice")
    public ResponseEntity<ResponseResult<String>> updatePrice() {
        this.tvService.updatePrice();
        return new ResponseEntity<>(new ResponseResult<>(null, "Price has been updated"), HttpStatus.OK);
    }
}
