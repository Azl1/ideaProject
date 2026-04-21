package com.kirillkotov.util;

public class Methods {
    /**
     * Sum of two numbers
     *
     * @param a integer number
     * @param b integer number
     * @return sum of numbers
     */
    public static int sum(int a, int b) {
        return a + b;
    }

    /**
     * Min of two numbers
     *
     * @param a integer number
     * @param b integer number
     * @return min of numbers
     */
    public static int min(int a, int b) {
        /*if(a < b){
            return a;
        }
        else{
            return b;
        }*/
        return a < b ? a : b;
    }

    /**
     * Min of four numbers
     *
     * @param a integer number
     * @param b integer number
     * @param c integer number
     * @param d integer number
     * @return min of numbers
     */
    public static int min(int a, int b, int c, int d){
        /*int res1 = Methods.min(a, b);
        int res2 = Methods.min(c, d);
        int res3 = Methods.min(res1, res2);
        return res3;*/
        return Methods.min(Methods.min(a, b), Methods.min(c, d));
    }

    /**
     * Factorial of number
     *
     * @param n integer number
     * @return factorial
     */
    public static int factorial(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    /**
     * Print all integer numbers in [a, b]
     *
     * @param a integer number
     * @param b integer number
     */
    public static void printAB(int a, int b) {
        for (int i = a; i <= b; i++) {
            System.out.print(i + " ");
        }
    }

    /**
     * Get all integer numbers in [a, b]
     *
     * @param a integer number
     * @param b integer number
     * @return string of all integer numbers
     */
    public static String getABNumbers(int a, int b) {
        String res = "";
        for (int i = a; i <= b; i++) {
            res += i + " ";
        }
        return res;
    }

    /**
     * Define if a number is odd
     *
     * @param n integer number
     * @return true if n is odd, else false
     */
    public static boolean isOdd(int n) {
        /*if(n % 2 != 0){
            return true;
        }
        else {
            return false;
        }*/
        return n % 2 != 0;
    }

    /**
     * Print all odd number in [a, b]
     *
     * @param a integer number
     * @param b integer number
     */
    public static void printOdd(int a, int b) {
        for (int i = a; i <= b; i++) {
            if (Methods.isOdd(i)) {
                System.out.print(i + " ");
            }
        }
    }
}
