package com.abdullaevaziz.fileuploaderspringbootfx.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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