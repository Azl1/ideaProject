package com.abdullaevaziz.controller;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Circle;
import com.abdullaevaziz.service.CircleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/circle")
public class CircleController {

    private CircleService circleService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setCircleService(CircleService circleService){
        this.circleService = circleService;
    }

    @GetMapping
    public void getAll( HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            List<Circle> circleGet = this.circleService.getAll();
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Circle>>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Circle>>(null, circleGet));
        } catch (IllegalArgumentException e) {
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Circle>>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Circle>>(e.getMessage(), null));
        }
    }
}
