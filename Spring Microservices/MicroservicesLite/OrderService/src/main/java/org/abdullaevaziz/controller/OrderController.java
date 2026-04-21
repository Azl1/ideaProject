package org.abdullaevaziz.controller;

import lombok.AllArgsConstructor;
import org.abdullaevaziz.dto.ResponseResult;
import org.abdullaevaziz.model.NotificationLong;
import org.abdullaevaziz.model.Order;
import org.abdullaevaziz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;


    @Autowired
    public void setOrderService(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Order>> add(@RequestBody Order order,
                                                      @RequestParam String email){
        try {
            this.orderService.add(order, email);
            return new ResponseEntity<>(new ResponseResult<>(null, order),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Order>>> getList() {
        return new ResponseEntity<>(new ResponseResult<>(null, this.orderService.getList()), HttpStatus.OK);
    }
}
