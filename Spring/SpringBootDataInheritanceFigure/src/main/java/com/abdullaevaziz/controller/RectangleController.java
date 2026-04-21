package com.abdullaevaziz.controller;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Rectangle;
import com.abdullaevaziz.service.RectangleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rectangle")
public class RectangleController {

    private RectangleService rectangleService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setRectangleService(RectangleService rectangleService){
        this.rectangleService = rectangleService;
    }

    @GetMapping
    public void getAll(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            List<Rectangle> rectangleGet = this.rectangleService.getAll();
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Rectangle>>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Rectangle>>(null, rectangleGet));
        } catch (IllegalArgumentException e) {
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Rectangle>>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Rectangle>>(e.getMessage(), null));
        }
    }
}
