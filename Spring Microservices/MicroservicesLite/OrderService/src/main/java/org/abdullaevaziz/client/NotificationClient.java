package org.abdullaevaziz.client;

import org.abdullaevaziz.dto.ResponseResult;
import org.abdullaevaziz.model.NotificationLong;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "Notification", url = "http://localhost:8082")
public interface NotificationClient {

    @PostMapping("/api/v1/notifications")
    ResponseEntity<ResponseResult<NotificationLong>> sendNotification(@RequestBody NotificationLong notificationLong);

    @GetMapping("/api/v1/notifications")
    ResponseEntity<ResponseResult<List<NotificationLong>>> findAll();

}
