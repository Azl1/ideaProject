package com.abdullaevaziz.model;


import java.util.Objects;

/**
 * Создать классы Triangle, Rectangle с реализацией интерфейса Figure
 */
public class Triangle implements Figure{

    private double a;
    private double b;
    private double c;

    public Triangle() {
    }

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
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

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public double square() {
        double p = perimeter() / 2;
        return (p - this.a) * (p - this.b) * (p - this.c);
    }

    @Override
    public double perimeter() {
        return this.a + this.b + this.c;
    }

    @Override
    public String getName() {
        return Figure.super.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(a, triangle.a) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(c, triangle.c) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    @Override
    public String toCSV() {
        return this.getName() + ";" + this.a + ";" + this.b + ";" + this.c + ";" + this.square() + ";" + this.perimeter();
    }
}
