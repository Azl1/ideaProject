package org.abdullaevaziz.controllers;


import io.swagger.v3.oas.annotations.Hidden;
import org.abdullaevaziz.dto.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping("/hidden")
public class HiddenController {
    @GetMapping
    public ResponseEntity<ResponseResult<String>> get(){
        return new ResponseEntity<>(new ResponseResult<>(null, "Hello World"), HttpStatus.OK);
    }
}
