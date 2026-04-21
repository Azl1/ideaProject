package com.kirillkotov.springsecurityjpa.controllers;

import com.kirillkotov.springsecurityjpa.model.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @PostMapping("/")
    public String homePost() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user/welcome")
    public String user(Authentication authentication) {
        if(authentication != null && authentication.isAuthenticated()){
            long id = ((UserDetailsImpl)authentication.getPrincipal()).getId();
            System.out.println(id);
        }
        else{
            System.out.println("No");
        }
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin/welcome")
    public String admin(Authentication authentication) {
        if(authentication.isAuthenticated()){
            long id = ((UserDetailsImpl)authentication.getPrincipal()).getId();
            System.out.println(id);
        }
        return ("<h1>Welcome Admin</h1>");
    }
}

