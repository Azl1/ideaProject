package com.abdullaevaziz.model;

/**
 * Квадрат
 */
public class Square extends Figure{

    public Square() {
    }

    public Square(double a) {
        super(a);
    }

    @Override
    public double square() {
        return getA() * getA();
    }

    @Override
    public double perimeter() {
        return getA() * 4; //TODO тут просто 4а
    }


}
