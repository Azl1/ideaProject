package com.abdullaevaziz.model;

/**
 * Круг
 */
public class Circle extends Figure{

    public Circle() {
    }

    public Circle(double a) {
        super(a);
    }

    @Override
    public double square() {
        return Math.PI * getA() * getA();
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * getA();
    }

}
