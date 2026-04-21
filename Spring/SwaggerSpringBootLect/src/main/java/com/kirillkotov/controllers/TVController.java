package com.kirillkotov.controllers;

import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.TV;
import com.kirillkotov.service.TVService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.StringJoiner;

@Tag(name = "TVController", description = "Контроллер для работы с телевизорами")
@RestController
@RequestMapping("/tv")
public class TVController {
    private TVService tvService;

    @Autowired
    public void setTvService(TVService tvService) {
        this.tvService = tvService;
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity<ResponseResult<TV>> add(@PathVariable long userId, @Valid @RequestBody TV tv) {
        this.tvService.add(userId, tv);
        return new ResponseEntity<>(new ResponseResult<>(null, tv), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<TV>>> get() {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.tvService.get()), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<TV>> get(@PathVariable long id) {
        TV tv = this.tvService.get(id);
        return new ResponseEntity<>(new ResponseResult<>(null, tv), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseResult<TV>> get(@RequestParam @Parameter(description = "Бренд телевизора", example = "Samsung", required = true) String brand,
                                                  @RequestParam @Parameter(description = "Модель телевизора", required = true) String model) {
        TV tv = this.tvService.get(brand, model);
        return new ResponseEntity<>(new ResponseResult<>(null, tv), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseResult<TV>> update(@Valid @RequestBody TV tv) {
        TV res = this.tvService.update(tv);
        return new ResponseEntity<>(new ResponseResult<>(null, res), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<TV>> delete(@PathVariable long id) {
        return new ResponseEntity<>(new ResponseResult<>(null, this.tvService.delete(id)), HttpStatus.OK);
    }
}
