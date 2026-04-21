package com.kirillkotov.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pens")
public class Pen extends Product {
    @NonNull
    private String color;
}
