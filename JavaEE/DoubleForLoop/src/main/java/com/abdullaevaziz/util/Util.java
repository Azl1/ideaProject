package com.abdullaevaziz.util;

public class Util {
    /**
     * 5. Даны числа m, n вывести таблицу m на n, заполненную числами 1
     * <p>
     * Пример:
     * Ввод
     * 3 4
     * Ответ
     * 1 1 1 1
     * 1 1 1 1
     * 1 1 1 1
     */
    public static void table1(int m, int n) {
        for (int i = 1; i <= m; i++) { //stroki
            for (int j = 1; j <= n; j++) { //stolbci
                System.out.print("1 ");
            }
            System.out.println();
        }
    }

    /**
     * 6. Даны числа m, n вывести таблицу m на n,
     * заполненную числами, равными номеру строки этой таблицы
     * <p>
     * Пример:
     * Ввод
     * 3 4
     * Ответ
     * 1 1 1 1
     * 2 2 2 2
     * 3 3 3 3
     */
    public static void table2(int m, int n) {
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    /**
     * 7. Даны числа m, n вывести таблицу m на n,
     * заполненную числами, равными номеру столбца этой таблицы
     * Пример:
     * Ввод
     * 3 4
     * Ответ
     * 1 2 3 4
     * 1 2 3 4
     * 1 2 3 4
     */
    public static void table3(int m, int n) {
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    /**
     * 8. По данному натуральному n выведите лесенку из n ступенек,
     * i-я ступенька состоит из чисел от 1 до i без пробелов.
     * <p>
     * Пример:
     * Ввод
     * 4
     * Ответ
     * 1
     * 1 2
     * 1 2 3
     * 1 2 3 4
     */
    public static void table4(int m) {
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    /**
     * 9. По данному натуральному n вычислите сумму 1!+2!+3!+...+n!.
     * В решении этой задачи можно использовать только вложенные циклы.
     * PS: Такое решение считается неоптимальным.
     * Оптимально мы решали уже эту задачу в разделе ForLoop одним циклом.
     * Здесь же требуется для каждого числа i от 1 до n посчитать его факториал i!,
     * то есть произведение всех чисел от 1 до i.
     * Переменную для факториала сделать локальной переменной внутри первого цикла,
     * переменную суммы внешней переменной до всех циклов
     */
    public static int factorial(int m) {
        int sum = 0;
        for (int i = 1; i <= m; i++) {
            int factorial = 1;
            for (int j = 1; j <= i; j++) {
                factorial *= j;
            }
            sum += factorial;
        }
        return sum;
    }

    /**
     * 10. На диапазоне от а до b найти количество простых чисел,
     * а так же вывести эти числа в консоль
     */
    public static int primeNumber(int a, int b) {
        int count = 0;
        for (int i = a; i <= b; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
                count++;
            }
        }
        return count;
    }

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 11. На диапазоне от а до b найти количество c,
     * а так же вывести четные совершенные числа
     */
    public static int findPerfectNumbers(int a, int b) {
        int count = 0;
        for (int i = a; i <= b; i++) {
            if (isPerfect(i)) {
                System.out.print(i + " ");
                count++;
            }
        }
        return count;
    }

    public static boolean isPerfect(int num) {
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum == num;
    }

    /**
     * 12. По данному натуральному n выведите лесенку из n ступенек в обратном порядке
     * Пример:
     * Ввод
     * 4
     * Ответ
     * 1 2 3 4
     * 1 2 3
     * 1 2
     * 1
     */
    public static void table8(int m) {
        for (int i = m; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }


    /**
     * 13. По данному натуральному n выведите лесенку
     * из n ступенек в полном обратном порядке, как в примере
     * Пример:
     * Ввод
     * 4
     * Ответ
     * 4 3 2 1
     * 3 2 1
     * 2 1
     * 1
     */
    public static void table9(int m) {
        for (int i = m; i >= 1; i--) {
            for (int j = i; j >= 1; j--) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    /**
     * 14. По данному натуральному n
     * выведите лесенку из n ступенек, заполненную числами и тильдами
     * Пример:
     * Ввод
     * 4
     * Ответ
     * 4 3 2 1
     * ~ 3 2 1
     * ~ ~2 1
     * ~ ~ ~1
     */
    public static void table10(int m) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("~ ");
            }
            for (int j = m - i; j > 0; j--) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }


    /**
     * 15. Даны числа m, n вывести таблицу m на n,
     * заполненную числами по порядку, под каждую ячейку таблицы выделяется минимально два знака
     * <p>
     * Пример:
     * Ввод
     * 3 4
     * Ответ
     * 1  2  3  4
     * 5  6  7  8
     * 9 10 11 12
     */
    public static void table11(int m, int n) {
        int number = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%2d ", number);
                number++;
            }
            System.out.println();
        }
    }

    /**
     * 16. Даны числа m, n вывести таблицу m на n,
     * заполненную числами по порядку,
     * в четных строках только четные числа,
     * в нечетных – нечетные,
     * под каждую ячейку таблицы выделяется минимально два знака
     * <p>
     * Пример:
     * Ввод
     * 3 4
     * Ответ
     * 1  3  5  7
     * 2  4  6  8
     * 9 11 13 15
     */

    public static void table12(int m, int n) {
        int number1 = 1;
        int number2 = 2;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i % 2 == 0) {

                    System.out.print(number1  + " ");
                    number1+=2;

                } else {
                    System.out.print(number2  + " ");
                    number2+=2;
                }
            }
            System.out.println();
        }
    }


}



