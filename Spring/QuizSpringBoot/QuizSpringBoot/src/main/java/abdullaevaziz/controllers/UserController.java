package abdullaevaziz.controllers;

import abdullaevaziz.dto.ResponseResult;
import abdullaevaziz.model.User;
import abdullaevaziz.model.UserType;
import abdullaevaziz.securety.jwt.JwtTokenProvider;
import abdullaevaziz.securety.jwt.JwtUser;
import abdullaevaziz.service.UserService;
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
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
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

    @GetMapping
    public ResponseEntity<ResponseResult<User>> getAuthenticatedUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            long id = ((JwtUser) authentication.getPrincipal()).getId();
            try {
                User user = userService.get(authentication, id);
                return new ResponseEntity<>(new ResponseResult<>(null, user), HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ResponseResult<>("Incorrect authentication", null), HttpStatus.BAD_REQUEST);
        }
    }

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

    @GetMapping("/token")
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<User>> geUser(Authentication authentication, @PathVariable long id) {
        try {
            User user = this.userService.get(authentication,id);
            return new ResponseEntity<>(new ResponseResult<>(null, user),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
