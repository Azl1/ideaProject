package com.abdullaevaziz.controller;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Circle;
import com.abdullaevaziz.model.Figure;
import com.abdullaevaziz.service.FigureService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/figure")
public class FigureController {

    private FigureService figureService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setFigureService(FigureService figureService) {
        this.figureService = figureService;
    }

    @GetMapping
    public void getAll(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            List<Figure> figureList = this.figureService.getAll();

            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Figure>>>() {})
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Figure>>(null, figureList));
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Figure>>>() {})
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Figure>>(e.getMessage(), null));
        }
    }

    @PostMapping
    public void add(@RequestBody Figure figure, HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Figure figureNew = this.figureService.add(figure);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Figure>>() {}).
                    writeValue(response.getOutputStream(), new ResponseResult<Figure>(null, figureNew));
        } catch (IllegalArgumentException e) {
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Figure>>() {}).
                    writeValue(response.getOutputStream(), new ResponseResult<Figure>(e.getMessage(), null));
        }
    }

    @GetMapping(path = "/{idFigure}")
    public void get(@PathVariable long idFigure, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Figure figureGet = this.figureService.get(idFigure);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Figure>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Figure>(null, figureGet));
        } catch (IllegalArgumentException e) {
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Figure>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Figure>(e.getMessage(), null));
        }
    }

    @DeleteMapping(path = "/{idFigure}")
    public void delete(@PathVariable long idFigure, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Figure figureDelete = this.figureService.delete(idFigure);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Figure>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Figure>(null, figureDelete));
        } catch (IllegalArgumentException e) {
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Figure>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Figure>(e.getMessage(), null));
        }
    }

    @PutMapping
    public void update(@RequestBody Figure figure,
                       HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Figure updatedFigure = this.figureService.update(figure);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Figure>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<>(null, updatedFigure));
        } catch (IllegalArgumentException | ClassCastException e) {
            e.printStackTrace();
            response.setStatus(400);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Figure>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Figure>(e.getMessage(), null));
        }
    }
}
