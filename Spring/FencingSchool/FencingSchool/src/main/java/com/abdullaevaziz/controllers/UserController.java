package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * • post – осуществляет прием данных и производит
     * регистрацию нового пользователя-администратора школы в системе.
     * Корректно обрабатывает существование пользователя в базе данных
     */
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

    /**
     * get – осуществляет отображение пользователя с заданным id
     */
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

    /**
     * get – осуществляет проверку соответствия логина и пароля для пользователя в базе данных
     */
    @GetMapping("/auth")
    public ResponseEntity<ResponseResult<User>> getUsersByLoginAndPassword(
            @RequestParam String login,
            @RequestParam String password) {
        try {
           User user = this.userService.get(login, password);
            return new ResponseEntity<>(new ResponseResult<User>(null, user), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * delete – осуществляет удаление пользователя с заданным id из базы данных
     */
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

}
