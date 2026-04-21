package com.kirillkotov.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "cats")
public class Cat extends Mammal {
    private int mouseExperience;

    public Cat(long id, String name, String color, int age,
               double weight, int mouseExperience) {
        super(id, name, color, age, weight);
        this.mouseExperience = mouseExperience;
    }
}
