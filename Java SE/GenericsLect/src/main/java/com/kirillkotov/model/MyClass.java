package com.kirillkotov.model;

public class MyClass<T /*extends Mammal*/>{
    private T a;

    public MyClass(T a) {
        this.a = a;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "a=" + a +
                '}';
    }
}
