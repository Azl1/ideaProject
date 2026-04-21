package com.kirillkotov.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.model.Mammal;
import com.kirillkotov.service.MammalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mammal")
public class MammalController {
    private MammalService mammalService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public MammalController(MammalService mammalService) {
        this.mammalService = mammalService;
    }

    @GetMapping
    public void get(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            List<Mammal> mammals = this.mammalService.get();

            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Mammal>>>() {})
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Mammal>>(null, mammals));
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Mammal>>>() {})
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Mammal>>(e.getMessage(), null));
        }
    }

    @PostMapping
    public void add(@RequestBody Mammal mammal, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Mammal mammalNew = this.mammalService.add(mammal);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Mammal>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Mammal>(null, mammalNew));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Mammal>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Mammal>(e.getMessage(), null));
        }
    }

    @DeleteMapping(path = "/{idMammal}")
    public void delete(@PathVariable long idMammal, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Mammal mammalDelete = this.mammalService.delete(idMammal);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Mammal>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Mammal>(null, mammalDelete));
        } catch (IllegalArgumentException e) {
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Mammal>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Mammal>(e.getMessage(), null));
        }
    }

    @GetMapping(path = "/{idMammal}")
    public void get(@PathVariable long idMammal, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Mammal mammalGet = this.mammalService.get(idMammal);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Mammal>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Mammal>(null, mammalGet));
        } catch (IllegalArgumentException e) {
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Mammal>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Mammal>(e.getMessage(), null));
        }
    }

    @PutMapping
    public void update(@RequestBody Mammal mammal,
                       HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Mammal updatedMammal = this.mammalService.update(mammal);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Mammal>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<>(null, updatedMammal));
        } catch (IllegalArgumentException | ClassCastException e) {
            e.printStackTrace();
            response.setStatus(400);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Mammal>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Mammal>(e.getMessage(), null));
        }
    }
}
