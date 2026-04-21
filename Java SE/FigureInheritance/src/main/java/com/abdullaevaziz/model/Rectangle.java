package com.abdullaevaziz.model;

/**
 * Прямоугольник
 */
public class Rectangle extends Figure {
    private double b;

    public Rectangle() {
    }

    public Rectangle(double b) {
        this.b = b;
    }

    @Override
    public double square() {
        return getA() * this.b;
    }

    @Override
    public double perimeter() {
        return (getA() + this.b) * 2;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ";" + this.b;
    }

}
