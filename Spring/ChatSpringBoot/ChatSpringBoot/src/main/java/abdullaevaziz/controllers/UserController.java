package abdullaevaziz.controllers;


import abdullaevaziz.dto.ResponseResult;
import abdullaevaziz.model.User;
import abdullaevaziz.service.UserService;
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
    public UserController(UserService userService){
        this.userService = userService;

    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseResult<User>> addUser(@RequestBody User user) {
        try {
            this.userService.addUser(user);
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

    @GetMapping("/login")
    public ResponseEntity<ResponseResult<User>> getUsersByFirstNameAndLastName(@RequestParam String login, @RequestParam String password) {
        User user = this.userService.getLogin(login, password);
        return new ResponseEntity<>(new ResponseResult<>(null, user), HttpStatus.OK);
    }

    @GetMapping("/listUsers")
    public ResponseEntity<ResponseResult<List<User>>> getListUsers() {
        List<User> list = this.userService.getListUsers();
        return new ResponseEntity<>(new ResponseResult<>(null, list),
                HttpStatus.OK);
    }

}
