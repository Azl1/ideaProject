package com.abdullaevaziz.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Util {

    /**
     * Посчитать, используя класс BigInteger значение n! Для n >= 50,
     * подавая разные значения n, высчитывая время работы программы
     */
    public static BigInteger factorial(int n) {
        BigInteger res = BigInteger.ONE;
        for (int i = 1; i <= n; ++i) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }


    /**
     * Посчитать, используя класс BigDecimal значение exp(x)
     * для достаточно больших чисел х. Для взятия числа е использовать Math.E
     */
    public static BigDecimal exp(int n){
        BigDecimal bigDecimal = BigDecimal.valueOf(Math.E);
        return bigDecimal.pow(n);
    }

}
