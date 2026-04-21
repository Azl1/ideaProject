package com.kirillkotov.program;

import com.kirillkotov.model.A;
import com.kirillkotov.model.Inter;

public class Main {
    public static<T> void f(Inter<T> inter){
        //inter.f()
    }

    public static void main(String[] args) {
        A a = new A(10);
        f(a);

        Inter<Integer> inter = new Inter<Integer>() {
            private int a;

            {
                setA(8);
            }

            public int getA() {
                return a;
            }

            public void setA(int a) {
                this.a = a;
            }
            @Override
            public Integer f(Integer a) {
                return a * 10;
            }
        };
        f(inter);
    }
}