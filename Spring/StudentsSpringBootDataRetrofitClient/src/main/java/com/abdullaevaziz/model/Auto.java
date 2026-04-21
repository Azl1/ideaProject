package com.abdullaevaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Auto {

    private long id;
    private String brand;
    private int power;
    private int year;

    private Student student;
}