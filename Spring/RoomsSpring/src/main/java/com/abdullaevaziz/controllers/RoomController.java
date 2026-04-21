package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Neighbourhood;
import com.abdullaevaziz.model.Room;
import com.abdullaevaziz.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public void setRoomService(RoomService roomService){
        this.roomService = roomService;
    }


    @PostMapping (path = "/host/{idHost}/neighbourhood/{idNeighbourhood}")
    public ResponseEntity<ResponseResult<Room>> add( @RequestBody Room room,
                                                     @PathVariable long idHost,
                                                     @PathVariable long idNeighbourhood) {
        try {
            this.roomService.add(room, idHost, idNeighbourhood);
            return new ResponseEntity<>(new ResponseResult<>(null, room), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/host/{idHost}/neighbourhood/{idNeighbourhood}")
    public ResponseEntity<ResponseResult<Room>> get(@PathVariable long idHost, @PathVariable long idNeighbourhood) {
        try {
            Room room = this.roomService.get(idHost, idNeighbourhood);
            return new ResponseEntity<>(new ResponseResult<>(null, room), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{idRoom}")
    public ResponseEntity<ResponseResult<Room>> get(@PathVariable long idRoom) {
        try {
            Room room = this.roomService.get(idRoom);
            return new ResponseEntity<>(new ResponseResult<>(null, room), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Room>>> getList() {
        try {
            List<Room> roomList = this.roomService.findAll();
            return new ResponseEntity<>(new ResponseResult<>(null, roomList), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/csv")
    public ResponseEntity<ResponseResult<String>> exportFile(@RequestParam String outputFileName) {
        try {
            this.roomService.exportToCSV(outputFileName);
            return new ResponseEntity<>(new ResponseResult<>(null, "OK"), HttpStatus.OK);
        } catch (IllegalArgumentException | IOException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("averagePrice")
    public ResponseEntity<ResponseResult<Map<String, Double>>> getAveragePrice() {
        try {
            Map<String, Double> boroughTotalPriceMap = this.roomService.averagePrice();
            return new ResponseEntity<>(new ResponseResult<>(null, boroughTotalPriceMap), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{boro}/{roomType}/{countRoom}")
    public ResponseEntity<ResponseResult<List<Room>>> getSearchRoom(@PathVariable String boro,
                                                                    @PathVariable String roomType,
                                                                    @PathVariable int countRoom) {
        try {
            List<Room> roomList = this.roomService.searchRoom(boro, roomType, countRoom);
            return new ResponseEntity<>(new ResponseResult<>(null, roomList), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
