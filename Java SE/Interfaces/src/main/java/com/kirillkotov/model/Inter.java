package com.kirillkotov.model;

public interface Inter<T> {
    int k = 100;

    T f(T a);

    default void test(){
        System.out.println("test");
    }
}
