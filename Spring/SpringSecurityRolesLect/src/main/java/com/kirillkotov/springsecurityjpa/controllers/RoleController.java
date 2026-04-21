package com.kirillkotov.springsecurityjpa.controllers;

import com.kirillkotov.springsecurityjpa.dto.ResponseResult;
import com.kirillkotov.springsecurityjpa.model.Role;
import com.kirillkotov.springsecurityjpa.model.User;
import com.kirillkotov.springsecurityjpa.model.UserDetailsImpl;
import com.kirillkotov.springsecurityjpa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;
    @Autowired
    public void setRoleService(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Role>> add(@RequestBody Role role) {
        try {
            this.roleService.add(role);
            return new ResponseEntity<>(new ResponseResult<>(null, role), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<Role>> get(@PathVariable Long userId){
        try {
            Role userIdGet = this.roleService.get(userId);
            return new ResponseEntity<>(new ResponseResult<>(null, userIdGet), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
