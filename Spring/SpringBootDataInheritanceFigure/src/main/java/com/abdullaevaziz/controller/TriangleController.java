package com.abdullaevaziz.controller;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Square;
import com.abdullaevaziz.model.Triangle;
import com.abdullaevaziz.service.SquareService;
import com.abdullaevaziz.service.TriangleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/triangle")
public class TriangleController {

    private TriangleService triangleService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setTriangleService(TriangleService triangleService){
        this.triangleService = triangleService;
    }

    @GetMapping
    public void getAll(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            List<Triangle> triangleGet = this.triangleService.getAll();
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Triangle>>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Triangle>>(null, triangleGet));
        } catch (IllegalArgumentException e) {
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Triangle>>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Triangle>>(e.getMessage(), null));
        }
    }
}
