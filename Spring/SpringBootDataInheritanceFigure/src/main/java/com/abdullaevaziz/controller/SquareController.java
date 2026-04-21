package com.abdullaevaziz.controller;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Rectangle;
import com.abdullaevaziz.model.Square;
import com.abdullaevaziz.service.RectangleService;
import com.abdullaevaziz.service.SquareService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/square")
public class SquareController {

    private SquareService squareService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setSquareService(SquareService squareService){
        this.squareService = squareService;
    }

    @GetMapping
    public void getAll( HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            List<Square> squareGet = this.squareService.getAll();
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Square>>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Square>>(null, squareGet));
        } catch (IllegalArgumentException e) {
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Square>>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Square>>(e.getMessage(), null));
        }
    }
}
