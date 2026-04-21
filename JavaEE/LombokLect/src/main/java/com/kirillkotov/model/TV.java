package com.kirillkotov.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TV {
    private String brand;
    @NonNull
    private String model;

    @NonNull
    private String color;

    @NonNull
    private int timeExpectancy;

    @NonNull
    @ToString.Exclude
    private double price;
}
