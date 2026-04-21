package com.kirillkotov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Создать модель данных студент(id, fio, age, num, salary)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String fio;

    @Column
    private int age;

    @Column(unique = true)
    private int num;

    @Column
    private int salary;



}
