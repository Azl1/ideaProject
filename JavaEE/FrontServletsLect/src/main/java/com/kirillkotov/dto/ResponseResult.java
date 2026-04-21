package com.kirillkotov.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kirillkotov.model.Part;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult <T>{
    private boolean result;

    private String message;

    private T data;


}
