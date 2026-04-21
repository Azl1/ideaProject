package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Coupon;
import com.abdullaevaziz.service.CouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/coupons")
public class CouponsController {


    private CouponsService couponsService;

    @Autowired
    public void setCouponsService(CouponsService couponsService) {
        this.couponsService = couponsService;
    }


    @PostMapping
    public ResponseEntity<ResponseResult<Coupon>> add(@RequestBody Coupon coupon) {
        try {
            this.couponsService.add(coupon);
            return new ResponseEntity<>(new ResponseResult<>(null, coupon), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Coupon>> get(@PathVariable long id) {
        try {
            Coupon coupon = this.couponsService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, coupon), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Coupon>>> getAllCoupons() {
        try {
            List<Coupon> coupons = couponsService.getAll();
            return new ResponseEntity<>(new ResponseResult<>(null, coupons), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/expiring-today")
    public ResponseEntity<ResponseResult<List<Coupon>>> getCouponsExpiringToday() {
        try {
            List<Coupon> coupons = couponsService.getExpiringToday();
            return new ResponseEntity<>(new ResponseResult<>(null, coupons), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/company/{company}")
    public ResponseEntity<ResponseResult<List<Coupon>>> getCouponsByCompany(@PathVariable("company") String company) {
        try {
            List<Coupon> coupons = couponsService.getByCompany(company);
            return new ResponseEntity<>(new ResponseResult<>(null, coupons), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Coupon>> delete(@PathVariable long id) {
        try {
            Coupon coupon = this.couponsService.deleteById(id);
            return new ResponseEntity<>(new ResponseResult<>(null, coupon), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/expired-today")
    public ResponseEntity<ResponseResult<List<Coupon>>> deleteCouponsByExpiringToday() {
        try {
            //TODO проверить вывзать метод вернуть список удаленных купонов
           List<Coupon> listExpiredToday = this.couponsService.deleteExpiredToday();
            return new ResponseEntity<>(new ResponseResult<>(null, listExpiredToday), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/company/{company}")
    public ResponseEntity<ResponseResult<List<Coupon>>> deleteCouponsByCompany(@PathVariable("company") String company) {
        try {
            List<Coupon> coupons = this.couponsService.deleteByCompany(company);
            return new ResponseEntity<>(new ResponseResult<>(null, coupons), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
