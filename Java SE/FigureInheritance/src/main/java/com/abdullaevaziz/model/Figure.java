package com.abdullaevaziz.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

/**
 * Геометрическая Фигура
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Circle.class, name = "circle"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"),
        @JsonSubTypes.Type(value = Square.class, name = "square"),
        @JsonSubTypes.Type(value = Triangle.class, name = "triangle"),
})
public abstract class Figure {

    /**
     * Создать базовый класс Figure – Геометрическая Фигура с одним вещественным полем.
     * Сделать конструктор с параметром. Добавить геттеры и сеттеры, вычисление площади,
     * периметра, equals, hashCode и toString.
     */

    private double a;

    public Figure() {
    }

    public Figure(double a) {
        this.a = a;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Double.compare(a, figure.a) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a);
    }

    @Override
    public String toString() {
        return "Figure{" +
                "a=" + a +
                '}';
    }

    public abstract double square();

    public abstract double perimeter();

    public String toCSV(){
        return this.getClass().getSimpleName() + ";" + this.a;
    }
}
