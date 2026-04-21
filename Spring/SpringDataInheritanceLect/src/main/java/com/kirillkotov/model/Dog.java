package com.kirillkotov.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "dogs")
public class Dog extends Mammal {
    private int bonesCount;
    @Column(unique = true)
    private String ownerNumber;

    public Dog(long id, String name, String color, int age,
               double weight, int bonesCount) {
        super(id, name, color, age, weight);
        this.bonesCount = bonesCount;
    }
}
