package com.abdullaevaziz.util;

public class Util {
    /**
     * 1.	Вычислить наибольшее значение из двух целых чисел
     */
    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * 2.Вычислить наибольшее значение из четырёх целых чисел.
     * Не использовать if, а вызывать метод из прошлой задачи 3 раза
     */
    public static int max(int a, int b, int c, int d) {
        return Util.max(max(a, b), max(c, d));
    }

    /**
     * 3.Вернуть true, если переданное число является простым,
     * или false, если непростым. В качестве типа возвращаемого значения использовать boolean.
     * Простое число -  это число, у которого только два делителя
     */
    public static boolean isSimple(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                count++;
            }
        }
        return count == 2;
    }

    /**
     * 4.Вывести на экран все простые числа на диапазоне от а до b.
     * Для проверки простоты использовать прошлый метод
     */
    public static void simpleNumber(int a, int b) {
        for (int i = a; i < b; i++) {
            if (isSimple(i)) {
                System.out.println(i + " ");
            }
        }

    }

    /**
     * 5.По дробному а и целому неотрицательному n вычислить значение а в степени n
     */
    public static double degree(double a, int n) {
        double res = 1;
        for (int i = 1; i <= n; i++) {
            res *= a;
        }
        return res;
    }

    /**
     * 6.Проверить, является ли переданное число четным числом
     */
    public static boolean even(int n) {
        return n % 2 == 0;
    }

    /**
     * 7.Вывести на экран только четные числа на отрезке от а до b
     */
    public static void evenNumbers(int a, int b) {
        for (int i = a; i <= b; i++) {
            if (even(i)) {
                System.out.println(i + " ");
            }
        }

    }

    /**
     * 8.Проверить, является ли переданное число совершенным.
     * Совершенное число – число, равное сумме своих делителей без учета последнего числа
     */
    public static boolean perfect(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum == n;
    }

    /**
     * 9.В виде строки вернуть все совершенные числа на диапазоне от а до b
     */
    public static String perfectString(int a, int b) {
        String res = "";
        for (int i = a; i <= b; i++) {
            if (perfect(i))
                res += i + " ";
        }
        return res;
    }
}