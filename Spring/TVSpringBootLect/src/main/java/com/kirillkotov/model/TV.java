package com.kirillkotov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.core.GenericTypeResolver;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TV {
    private long id;
    private String brand;
    private String model;
    private int timeExpectancy;
    private double price;
}
