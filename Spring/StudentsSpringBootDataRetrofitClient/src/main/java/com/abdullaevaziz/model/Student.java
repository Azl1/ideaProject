package com.abdullaevaziz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private long id;
    private String fio;
    private int age;
    private int num;
    private double salary;

    @JsonIgnore
    private List<Auto> autos = new ArrayList<>();
}
