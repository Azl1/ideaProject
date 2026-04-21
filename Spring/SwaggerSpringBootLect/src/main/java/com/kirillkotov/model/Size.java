package com.kirillkotov.model;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class Size {
    @Positive(message = "Width must be positive")
    private int width;

    @Positive(message = "Height must be positive")
    private int height;

    @Positive(message = "Length must be positive")
    private int length;
}


