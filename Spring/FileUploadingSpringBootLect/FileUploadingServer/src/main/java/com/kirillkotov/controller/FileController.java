package com.kirillkotov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Return file by filename for showing
     */
    @GetMapping(value = "/{fileName}")
    public void getFile(HttpServletResponse response,
                        @PathVariable String fileName) throws IOException {
        try {
            this.fileService.download(response, fileName);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            response.setContentType("application/json;charset=utf-8");
            new ObjectMapper().writeValue(response.getWriter(),
                    new ResponseResult<>(null, e.getMessage()));
        }
    }

    /**
     * Post file and argument
     */
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseResult<String>> save(@RequestPart String arg,
                                                       @RequestPart MultipartFile document) {
        try {
            this.fileService.save(arg, document);
            return new ResponseEntity<>(new ResponseResult<>(null,
                    "Successfully"), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(new ResponseResult<>(null, "Fail"), HttpStatus.BAD_REQUEST);
        }
    }
}
