package com.abdullaevaziz.program;

public class Main {
    public static void main(String[] args) {

        /**
         * 4. Вывести на экран, используя только одну команду System.out.printf
         * форматированный текст использованием этих переменных так, чтобы:
         * ● Целые переменные имени минимальное количество знаков: 2,
         * а недостающие заполнялись автоматически нулями
         * ● Вещественные переменные имели не более двух знаков после запятой
         */

        long l = 5L;
        int i = 2;
        double db =  5.5;
        float f = 5.55f;
        char c = '$';
        String s = "GeeksforGeeks";

        System.out.printf("My answer is %02d%c\n", l, c);
        System.out.printf("My answer is %02d%c\n", i, c);
        System.out.printf("My answer is %.3g%c\n", db, c);
        System.out.printf("My answer is %.3g%c\n", f, c);
        System.out.printf("My answer is %02d%c\n", l, c);
        System.out.printf("My Company name is %s\n", s);

        /**
         * 5.Сформировать новую строку, используя String.format по алгоритму, описанному выше, вывести ее на экран
         */
        System.out.println("----------------------------------------");
        String res1 = String.format("My answer is %02d%c", l, c);
        System.out.println(res1);
        String res2 = String.format("My answer is %02d%c", i, c);
        System.out.println(res2);
        String res3 = String.format("My answer is %.3g%c", db, c);
        System.out.println(res3);
        String res4 = String.format("My answer is %.3g%c", f, c);
        System.out.println(res4);
        String res5 = String.format("My answer is %02d%c", l, c);
        System.out.println(res5);
        String res6 = String.format("My Company name is %s", s);
        System.out.println(res6);
    }
}