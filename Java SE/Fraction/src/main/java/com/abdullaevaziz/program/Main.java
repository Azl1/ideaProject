package com.abdullaevaziz.program;

import com.abdullaevaziz.model.Fraction;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        //TODO тут создать объекты класса FRaction с вызовом всех конструкторов
        //TODO обраюботаь исключение
        Fraction fraction1 = new Fraction(2,7);
        Fraction fraction2 = new Fraction(6,4);
        Fraction fraction3 = new Fraction(8,5);

        Fraction[] fractions = new Fraction[]{fraction1, fraction2, fraction3};


        /**
         * Произвести сортировку массива дробей в порядке возрастания и в порядке убывания
         */
        Comparator<Fraction> fComporator2 = new Comparator<Fraction>() {
            @Override
            public int compare(Fraction o1, Fraction o2) {
                return -o1.compareTo(o2);
            }
        };
        Fraction f1 = fraction1.sum(fractions);
        System.out.println(f1);
        Fraction f2 = fraction1.maxFraction(fractions);
        System.out.println(f2);

        Arrays.sort(fractions);
        System.out.println(Arrays.toString(fractions));
        Arrays.sort(fractions, fComporator2);
        System.out.println(Arrays.toString(fractions));



    }
}