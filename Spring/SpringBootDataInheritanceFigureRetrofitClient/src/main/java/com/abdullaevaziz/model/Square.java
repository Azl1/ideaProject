package com.abdullaevaziz.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "squares")
public class Square extends Figure{

    public Square(double a) {
        this.setA(a);
    }

    public Square(Long id, double a) {
        super(id, a);
    }
}
