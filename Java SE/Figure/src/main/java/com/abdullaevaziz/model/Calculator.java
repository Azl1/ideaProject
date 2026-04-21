package com.abdullaevaziz.model;

import java.util.Objects;

/**
 * Создать класс Calculator,
 * в качестве поля класса указать объект типа Figure,
 * с которым будет работать калькулятор.
 * В конструкторе класса произвести инициализацию данного поля
 */
public class Calculator {

    private Figure figure;

    public Calculator() {
    }

    public Calculator(Figure figure) {
        this.figure = figure;
    }


    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    /**
     * В классе Calculator написать метод calculate,
     * принимающий на вход экземпляр перечисления и
     * возвращающий double – результат вычислений калькулятора для заданной фигуры в зависимости
     * от переданного экземпляра перечисления
     */
    public double calculate(Functor functor) {
        double res = 0;
        switch (functor) {
            case SQUARE -> {
                res = figure.square();
            }
            case PERIMETR -> {
                res = figure.perimeter();
            }
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculator that = (Calculator) o;
        return Objects.equals(figure, that.figure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(figure);
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "figure=" + figure +
                '}';
    }


}
