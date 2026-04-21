package com.abdullaevaziz.studentsspringbootdataclient.model;

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
    private long studentId;

    public Auto( String brand, int power, int year) {
        this.brand = brand;
        this.power = power;
        this.year = year;
    }
}
