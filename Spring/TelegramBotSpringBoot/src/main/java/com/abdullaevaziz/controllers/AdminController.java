package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Admin;
import com.abdullaevaziz.model.AdminDetailsImpl;
import com.abdullaevaziz.model.TelegramUser;
import com.abdullaevaziz.service.AdminService;
import com.abdullaevaziz.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    private TelegramUserService telegramUserService;

    @Autowired
    public void setTelegramUserService(TelegramUserService telegramUserService) {
        this.telegramUserService = telegramUserService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Admin>> post(@RequestBody Admin admin) {
        try {
            this.adminService.add(admin);
            return new ResponseEntity<>(new ResponseResult<>(null, admin), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<Admin>> get(Authentication authentication) {
        long id = ((AdminDetailsImpl) authentication.getPrincipal()).getId();
        try {
            Admin admin = this.adminService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, admin), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseResult<List<TelegramUser>>> get() {
        List<TelegramUser> list = this.telegramUserService.getList();
        return new ResponseEntity<>(new ResponseResult<>(null, list), HttpStatus.OK);
    }
}
