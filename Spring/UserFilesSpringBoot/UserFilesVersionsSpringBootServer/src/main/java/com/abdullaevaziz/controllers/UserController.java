package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.security.jwt.JwtTokenProvider;
import com.abdullaevaziz.security.jwt.JwtUser;
import com.abdullaevaziz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/authentication")
    public ResponseEntity<ResponseResult<String>> login(@RequestParam String username,
                                                        @RequestParam String password) {
        Authentication authentication
                = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        String token = jwtTokenProvider.createToken(jwtUser);

        return new ResponseEntity<>(new ResponseResult<>(null, token), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseResult<User>> add(@RequestBody User user) {
        try {
            User userNew = this.userService.add(user);
            return new ResponseEntity<>(new ResponseResult<>(null, userNew),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
