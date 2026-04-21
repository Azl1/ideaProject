package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Neighbourhood;
import com.abdullaevaziz.model.Room;
import com.abdullaevaziz.service.NeighbourhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/neighbourhoods")
public class NeighbourhoodController {

    private NeighbourhoodService neighbourhoodService;

    @Autowired
    public void setNeighbourhoodService(NeighbourhoodService neighbourhoodService){
        this.neighbourhoodService = neighbourhoodService;
    }


    @PostMapping
    public ResponseEntity<ResponseResult<Neighbourhood>> add(@RequestBody Neighbourhood neighbourhood) {
        try {
            this.neighbourhoodService.add(neighbourhood);
            return new ResponseEntity<>(new ResponseResult<>(null, neighbourhood), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Neighbourhood>> get(@PathVariable long id) {
        try {
            Neighbourhood neighbourhood = this.neighbourhoodService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, neighbourhood), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Neighbourhood>>> getList() {
        try {
            List<Neighbourhood> neighbourhoodList = this.neighbourhoodService.findAll();
            return new ResponseEntity<>(new ResponseResult<>(null, neighbourhoodList), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping (path = "/csv")
    public ResponseEntity<ResponseResult<String>> exportFile(@RequestParam String outputFileName) {
        try {
            this.neighbourhoodService.exportToCSV(outputFileName);
            return new ResponseEntity<>(new ResponseResult<>(null, "OK"), HttpStatus.OK);
        } catch (IllegalArgumentException | IOException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
