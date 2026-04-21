package com.abdullaevaziz.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "circles")
public class Circle extends Figure{

    public Circle(double a) {
        this.setA(a);
    }

    public Circle(Long id, double a) {
        super(id, a);
    }
}
