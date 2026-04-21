package com.abdullaevaziz.userfilesspringbootfx.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class ResponseResult<T> {
    private String message;
    private T data;
}