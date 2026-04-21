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

    @GetMapping("/search")
    public ResponseEntity<ResponseResult<TV>> get(@RequestParam String brand, @RequestParam String model) {
        try {
            TV tv = this.tvService.get(brand, model);
            return new ResponseEntity<>(new ResponseResult<>(null, tv), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/search/brands")
    public ResponseEntity<ResponseResult<List<TV>>> get(@RequestBody List<String> brands) {
        List<TV> tvs = this.tvService.get(brands);
        return new ResponseEntity<>(new ResponseResult<>(null, tvs), HttpStatus.OK);
    }

    @GetMapping(path = "/search/firstName")
    public ResponseEntity<ResponseResult<List<TV>>> get(@RequestParam String firstName) {
        List<TV> users = this.tvService.getByUserFirstName(firstName);
        return new ResponseEntity<>(new ResponseResult<>(null, users), HttpStatus.OK);
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

    @DeleteMapping(path = "/delete/brand_color")
    public ResponseEntity<ResponseResult<List<TV>>> delete(@RequestParam String brand, @RequestParam String color) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.tvService.deleteByBrandAndColor(brand, color)), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/brand_model")
    public void deleteTVs(@RequestParam String brand, @RequestParam String model) {
        this.tvService.delete(brand, model);
    }

    @PutMapping("/update/brand_color")
    void updateColor(@RequestParam String brand, @RequestParam String color) {
        this.tvService.updateColor(brand, color);
    }
}
