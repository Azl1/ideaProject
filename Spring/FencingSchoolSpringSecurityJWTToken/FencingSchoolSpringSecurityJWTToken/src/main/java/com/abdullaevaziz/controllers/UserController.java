package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Apprentice;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.securety.jwt.JwtTokenProvider;
import com.abdullaevaziz.securety.jwt.JwtUser;
import com.abdullaevaziz.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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


   /* @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/
    private ObjectMapper objectMapper = new ObjectMapper();
    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * • get – осуществляет проверку соответствия логина и
     * пароля для пользователя в базе данных, возвращает
     * объект User(c указанием типа объекта) по объекту Authentification
     * (все аутентифицированные)
     */
    /*@GetMapping
    public ResponseEntity<ResponseResult<User>> getAuthenticatedUser(Authentication authentication) {
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
    }*/

    @GetMapping
    public void getAuthenticatedUser(Authentication authentication,  HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        if (authentication != null && authentication.isAuthenticated()) {
            long id = ((JwtUser) authentication.getPrincipal()).getId();
            try {
                User userGet = userService.get(id);
                this.objectMapper.writerFor(new TypeReference<ResponseResult<User>>() {
                        })
                        .writeValue(response.getOutputStream(),
                                new ResponseResult<User>(null, userGet));
            } catch (IllegalArgumentException e) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                this.objectMapper.writeValue(response.getOutputStream(),
                        new ResponseResult<Apprentice>(e.getMessage(), null));
            }
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Apprentice>("Incorrect authentication", null));
        }
    }


    /**
     * • get – осуществляет отображение
     * пользователя с заданным id (admin, trainer и apprentice – только себя)
     */
    /*@GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<User>> get(@PathVariable long id) {
        try {
            User user = this.userService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, user),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }*/
    @GetMapping(path = "/{id}")
    public void get(@PathVariable long id, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            User userGet = this.userService.get(id);
            response.setStatus(HttpStatus.OK.value());
            this.objectMapper.writerFor(new TypeReference<ResponseResult<User>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<User>(null, userGet));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Apprentice>(e.getMessage(), null));
        }
    }

    /**
     * • delete – осуществляет удаление пользователя
     * с заданным id из базы данных (admin)
     */
    /*@DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult<User>> delete(@PathVariable long id){
        try {
            User userDelete = this.userService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, userDelete),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }*/
    @DeleteMapping("/{id}")
    public void delete(@PathVariable  long id, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            User userDelete = this.userService.delete(id);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<User>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<User>(null, userDelete));
        }  catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Apprentice>(e.getMessage(), null));
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
}
