package com.abdullaevaziz.studentsspringbootdataclient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @ToString.Exclude
    private List<Auto> autos = new ArrayList<>();

    public Student(String fio, int age, int num, double salary){
        this.fio = fio;
        this.age = age;
        this.num = num;
        this.salary = salary;
    }
}
