package com.kirillkotov.springsecurityjpa.controllers;

import com.kirillkotov.springsecurityjpa.dto.ResponseResult;
import com.kirillkotov.springsecurityjpa.model.User;
import com.kirillkotov.springsecurityjpa.model.UserDetailsImpl;
import com.kirillkotov.springsecurityjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<User>> post(@RequestBody User user) {
        try {
            this.userService.add(user);
            return new ResponseEntity<>(new ResponseResult<>(null, user), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<User>> get(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            long id = ((UserDetailsImpl) authentication.getPrincipal()).getId();
            try {
                User user = userService.get(id);
                return new ResponseEntity<>(new ResponseResult<>(null, user), HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ResponseResult<>("Incorrect authentification", null), HttpStatus.BAD_REQUEST);
        }
    }
}
