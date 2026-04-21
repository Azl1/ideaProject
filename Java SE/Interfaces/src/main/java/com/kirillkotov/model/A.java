package com.kirillkotov.model;

public class A implements Inter<Integer>{
    private int b;

    public A(int b) {
        this.b = b;
    }

    @Override
    public Integer f(Integer a) {
        return a * this.b;
    }
}
