package com.abdullaevaziz.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "triangles")
public class Triangle extends Figure{

    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.setA(a);
        this.b = b;
        this.c = c;
    }

    public Triangle(Long id, double a, double b, double c) {
        super(id, a);
        this.b = b;
        this.c = c;
    }
}