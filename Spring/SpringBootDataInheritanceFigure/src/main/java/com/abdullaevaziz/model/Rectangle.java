package com.abdullaevaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rectangles")
public class Rectangle extends Figure{

    private double b;
    public Rectangle(double a) {
        this.setA(a);
    }
}
