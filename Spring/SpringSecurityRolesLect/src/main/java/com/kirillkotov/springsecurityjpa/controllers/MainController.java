package com.kirillkotov.springsecurityjpa.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user/welcome")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin/welcome")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }
}

