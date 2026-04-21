package com.kirillkotov.springsecurityjpa.controllers;

import com.kirillkotov.springsecurityjpa.dto.ResponseResult;
import com.kirillkotov.springsecurityjpa.model.User;
import com.kirillkotov.springsecurityjpa.model.UserDetailsImpl;
import com.kirillkotov.springsecurityjpa.service.RoleService;
import com.kirillkotov.springsecurityjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
            return new ResponseEntity<>(new ResponseResult<>("Incorrect authentication", null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/{userId}/{roleId}")
    public ResponseEntity<ResponseResult<User>> addRole(@PathVariable long userId, @PathVariable long roleId) {
        try {
            User user = this.userService.addRole(userId, roleId);
            return new ResponseEntity<>(new ResponseResult<>(null, user), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
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
}
