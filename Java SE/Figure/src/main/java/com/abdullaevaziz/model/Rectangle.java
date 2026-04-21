package com.abdullaevaziz.model;


import java.util.Objects;

/**
 * Создать классы Triangle, Rectangle с реализацией интерфейса Figure
 */
public class Rectangle implements Figure{

    private double a;
    private double b;

    public Rectangle() {
    }

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }


    @Override
    public double square() {
        return  this.a * this.b;
    }

    @Override
    public double perimeter() {
        return (this.a + this.b) * 2;
    }

    @Override
    public String getName() {
        return Figure.super.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(a, rectangle.a) == 0 && Double.compare(b, rectangle.b) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "r=" + a +
                ", b=" + b +
                '}';
    }

    @Override
    public String toCSV() {
        return this.getName() + ";" + this.a + ";" + this.b + ";" + this.square() + ";" + this.perimeter();
    }
}
