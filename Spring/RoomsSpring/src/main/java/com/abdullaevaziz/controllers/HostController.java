package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Host;
import com.abdullaevaziz.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hosts")
public class HostController {

    private HostService hostService;

    @Autowired
    public void setHostService(HostService hostService){
        this.hostService = hostService;
    }


    @PostMapping
    public ResponseEntity<ResponseResult<Host>> add(@RequestBody Host host) {
        try {
            this.hostService.add(host);
            return new ResponseEntity<>(new ResponseResult<>(null, host), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Host>> get(@PathVariable long id) {
        try {
            Host host = this.hostService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, host), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
