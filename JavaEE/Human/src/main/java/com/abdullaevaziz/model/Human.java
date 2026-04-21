package com.abdullaevaziz.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Human {
    @NonNull
    private String name;
    @NonNull
    private int age;

    private int salary;

    private int weight;

}
