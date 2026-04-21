package com.abdullaevaziz.arrays;

/**
 * 7. Создать классы SumCalculator и MultCalculator
 * с реализацией операций сложения и умножения соответственно
 */
public class SumCalculator extends NumberCalculator{

    public SumCalculator(int n) {
        super(n);
    }

    @Override
    public int operation(int a, int b) {
        return a + b;
    }

    @Override
    public String toString() {
        return "SumCalculator{}";
    }
}
