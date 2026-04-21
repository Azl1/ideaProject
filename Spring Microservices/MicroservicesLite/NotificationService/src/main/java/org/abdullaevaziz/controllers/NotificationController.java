package org.abdullaevaziz.controllers;

import lombok.RequiredArgsConstructor;
import org.abdullaevaziz.dto.ResponseResult;
import org.abdullaevaziz.model.NotificationLong;
import org.abdullaevaziz.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private NotificationService notificationService;

    @Autowired
    public void setNotificationService(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<NotificationLong>> add(@RequestBody NotificationLong notificationLong) {
        try {
            this.notificationService.add(notificationLong);
            return new ResponseEntity<>(new ResponseResult<>(null, notificationLong),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<NotificationLong>>> getList() {
        return new ResponseEntity<>(new ResponseResult<>(null, this.notificationService.getList()), HttpStatus.OK);
    }
}
