package com.kirillkotov.controllers;


import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.User;
import com.kirillkotov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.StringJoiner;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<User>> add(@Valid @RequestBody User user) {
        this.userService.add(user);
        return new ResponseEntity<>(new ResponseResult<>(null, user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<User>>> get() {
        List<User> users = this.userService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, users), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<User>> get(@PathVariable long id) {
        User user = this.userService.get(id);
        return new ResponseEntity<>(new ResponseResult<>(null, user), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<User>> delete(@PathVariable long id) {
        User user = this.userService.delete(id);
        return new ResponseEntity<>(new ResponseResult<>(null, user), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseResult<User>> put(@Valid @RequestBody User user) {
        User res = this.userService.update(user);
        return new ResponseEntity<>(new ResponseResult<>(null, res), HttpStatus.OK);
    }
}
