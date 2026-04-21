package com.kirillkotov.controllers;


import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.User;
import com.kirillkotov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<User>> add(@RequestBody User user) {
        try {
            this.userService.add(user);
            return new ResponseEntity<>(new ResponseResult<>(null, user), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<User>>> get() {
        List<User> users = this.userService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, users), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<User>> get(@PathVariable long id) {
        try {
            User user = this.userService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, user), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<User>> delete(@PathVariable long id) {
        try {
            User user = this.userService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, user), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<User>> put(@RequestBody User user){
        try {
            User res = this.userService.update(user);
            return new ResponseEntity<>(new ResponseResult<>(null, res), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/firstname")
    public List<User> getUsersByFirstName(@RequestParam String firstName) {
        return this.userService.get(firstName);
    }

    @GetMapping("/search")
    public List<User> getUsersByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return this.userService.get(firstName, lastName);
    }

    @GetMapping("/all/asc")
    public List<User> getAllByOrOrderByFirstNameAsc() {
        return this.userService.getAllByOrOrderByFirstNameAsc();
    }

    @GetMapping("/all/desc")
    public List<User> getAllByOrOrderByFirstNameDesc() {
        return this.userService.getAllByOrOrderByFirstNamDesc();
    }
}
