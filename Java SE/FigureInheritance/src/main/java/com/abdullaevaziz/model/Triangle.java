package com.abdullaevaziz.model;

/**
 * Треугольник
 */
public class Triangle extends Figure {
    private double b;
    private double c;

    public Triangle() {
    }

    public Triangle(double a, double b, double c) {
        super(a);
        this.b = b;
        this.c = c;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public double square() {
        double p = perimeter() / 2;
        return (p - getA()) * (p - this.b) * (p - this.c);
    }

    @Override
    public double perimeter() {
        return getA() + this.b + this.c;
    }

}
