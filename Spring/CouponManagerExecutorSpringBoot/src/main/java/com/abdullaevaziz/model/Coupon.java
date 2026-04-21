package com.abdullaevaziz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 1. Создать модель данных Coupon
 * (id, name, company, regDate - LocalDate, expire - int).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coupons",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "company"})})
public class Coupon {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String company;


    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate regDate = LocalDate.now();

    /**
     * Поле expire показывает срок действия купона в днях
     */
    private int expire;

}
