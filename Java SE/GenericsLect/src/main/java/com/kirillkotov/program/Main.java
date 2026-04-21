package com.kirillkotov.program;

import com.kirillkotov.model.Cat;
import com.kirillkotov.model.Mammal;
import com.kirillkotov.model.MyClass;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void f(Integer a){
        System.out.println(a);
    }

    public static void f(String a){
        System.out.println(a);
    }

    public static void f(Double a){
        System.out.println(a);
    }

    public static <T> void genF(T a){
        System.out.println(a);
    }

    public static <T extends Mammal> void genF2(T a){
        System.out.println(a);
        a.voice();
    }

    public static void f3(Mammal mammal){
        mammal.voice();
    }

    public static void f4(ArrayList<Mammal> mammal){

    }

    public static void f5(ArrayList<?> mammal){

    }

    public static void f6(ArrayList<? extends Mammal> mammal){

    }

    public static <T extends Mammal> void f7(ArrayList<T> mammal){

    }

    public static <T> T[] newArr(T[] mass){
        //T[] res = new T[10];
        T[] res = (T[]) Array.newInstance(mass.getClass().getComponentType(), 10);
        return res;
    }

    public static void main(String[] args) {
        f(5);
        f("5");
        f(5.6);

        genF(4);
        genF(4L);
        genF(4.6f);
        genF(4.6);

        genF(new Mammal());
        genF2(new Mammal());

        MyClass<Integer> myClass = new MyClass<>(5);


        f3(new Mammal());
        f3(new Cat());

        f4(new ArrayList<Mammal>());
        //f4(new ArrayList<Cat>()); //! not working
        f5(new ArrayList<Cat>());

        f6(new ArrayList<Cat>());

        f7(new ArrayList<Cat>());
    }
}
