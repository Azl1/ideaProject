package com.abdullaevaziz.controllers;


import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.History;
import com.abdullaevaziz.model.TelegramUser;
import com.abdullaevaziz.service.HistoryService;
import com.abdullaevaziz.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/telegram")
public class TelegramController {
    private TelegramService telegramService;
    private HistoryService historyService;

    @Autowired
    public void setTelegramService(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Autowired
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/message/{chatId}")
    public ResponseEntity<ResponseResult<String>> send(@PathVariable long chatId, @RequestParam String message) {
        try {
            TelegramUser telegramUser = telegramService.sendMessage(chatId, message);
            return new ResponseEntity<>(new ResponseResult<>(null, "OK"), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/image/{chatId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseResult<TelegramUser>> sendImage(@PathVariable long chatId, @RequestPart MultipartFile file) {
        try {
            TelegramUser telegramUser = telegramService.sendImage(chatId, file);
            return ResponseEntity.ok(new ResponseResult<>("OK", telegramUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseResult<>(e.getMessage(), null));
        }
    }

    @PostMapping(value = "/audio/{chatId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseResult<TelegramUser>> sendAudio(@PathVariable long chatId, @RequestPart MultipartFile file) {
        try {
            TelegramUser telegramUser = telegramService.sendAudio(chatId, file);
            return ResponseEntity.ok(new ResponseResult<>("OK", telegramUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseResult<>(e.getMessage(), null));
        }
    }

    @PostMapping(value = "/document/{chatId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseResult<TelegramUser>> sendDocument(@PathVariable long chatId, @RequestPart MultipartFile file) {
        try {
            TelegramUser telegramUser = telegramService.sendDocument(chatId, file);
            return ResponseEntity.ok(new ResponseResult<>("OK", telegramUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseResult<>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/deleteMessage/{chatId}/{messageId}")
    public ResponseEntity<ResponseResult<History>> deleteMessage(@PathVariable long chatId, @PathVariable int messageId) {
        try {
            History historyDelete = this.telegramService.deleteMessage(chatId, messageId);
            return new ResponseEntity<>(new ResponseResult<History>(null, historyDelete), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteUser/{chatId}")
    public ResponseEntity<ResponseResult<TelegramUser>> deleteUser(@PathVariable long chatId) {
        try {
            TelegramUser telegramUser = this.telegramService.deleteMessageChat(chatId);
            return new ResponseEntity<>(new ResponseResult<TelegramUser>(null, telegramUser), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{chatId}")
    public ResponseEntity<ResponseResult<TelegramUser>> getTelegramUser(@PathVariable long chatId) {
        try {
            TelegramUser telegramUser = this.telegramService.get(chatId);
            return new ResponseEntity<>(new ResponseResult<TelegramUser>(null, telegramUser), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    /*@GetMapping("/historyGet/{historyId}")
    public ResponseEntity<ResponseResult<History>> getHistory(@PathVariable long historyId) {
        try {
            History historyGet = this.historyService.(historyId);
            return new ResponseEntity<>(new ResponseResult<History>(null, historyGet), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }*/
}
