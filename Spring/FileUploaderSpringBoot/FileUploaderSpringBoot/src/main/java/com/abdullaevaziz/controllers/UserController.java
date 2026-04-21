package com.abdullaevaziz.controllers;



import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.model.UserType;
import com.abdullaevaziz.securety.jwt.JwtTokenProvider;
import com.abdullaevaziz.securety.jwt.JwtUser;
import com.abdullaevaziz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseResult<User>> addUser(@RequestBody User user) {
        try {
            this.userService.addUser(user, UserType.USER);
            return new ResponseEntity<>(new ResponseResult<>(null, user),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    //TODO сделать так чтобы этот запрос был доступен тока админам
    @PostMapping("/admin")
    public ResponseEntity<ResponseResult<User>> addAdmin(@RequestBody User user) {
        try {
            this.userService.addAdmin(user, UserType.ADMIN);
            return new ResponseEntity<>(new ResponseResult<>(null, user),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<User>> get(@PathVariable long id) {
        try {
            User user = this.userService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, user),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authentication")
    public ResponseEntity<ResponseResult<String>> login(@RequestParam String username,
                                                        @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(username, password));

            User user = userService.findByUsername(username);

            JwtUser jwtUser = (JwtUser) authentication.getPrincipal();

            String token = jwtTokenProvider.createToken(jwtUser);
            return new ResponseEntity<>(new ResponseResult<>(null, token), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping("/listUsers")
    public ResponseEntity<ResponseResult<List<User>>> getListUsers(Authentication authentication) {
        List<User> list = this.userService.getListUsers(authentication);
        return new ResponseEntity<>(new ResponseResult<>(null, list),
                HttpStatus.OK);
    }

}
