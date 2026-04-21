package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.UserFile;
import com.abdullaevaziz.service.UserFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/userFile")
public class UserFileController {
    private UserFileService userFileService;

    @Autowired
    public UserFileController(UserFileService userFileService) {
        this.userFileService = userFileService;
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @PostMapping
    public ResponseEntity<ResponseResult<UserFile>> uploadFile(Authentication authentication,
                                                               MultipartFile file) {
        try {
            UserFile userFileNew = this.userFileService.saveFile(authentication, file);
            return new ResponseEntity<>(new ResponseResult<>(null, userFileNew),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<UserFile>>> getFilesList(Authentication authentication) {
        try {
            List<UserFile> userFileList = this.userFileService.getUserFiles(authentication);
            return new ResponseEntity<>(new ResponseResult<>(null, userFileList), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/file")
    public void downloadFile(Authentication authentication,
                             @RequestParam String filename, HttpServletResponse response) throws IOException {
        try {
            this.userFileService.download(authentication, filename, response);

        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            response.setContentType("application/json;charset=utf-8");
            new ObjectMapper().writeValue(response.getWriter(),
                    new ResponseResult<>(e.getMessage(), null));
        }
    }
}
