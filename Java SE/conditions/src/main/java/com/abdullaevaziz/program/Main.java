package com.abdullaevaziz.program;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
         * Даны два целых числа. Выведите значение наибольшего из них
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int res;
        if(a > b){
            res = a;
        }
        else{
            res = b;
        }
        System.out.println(res);*/

        /**
         * Даны два целых числа. Программа должна вывести число 1,
         * если первое число больше второго, число 2,
         * если второе больше первого или число 0, если они равны.
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int res;
        if(a > b) {
            res = 1;
        } else if (a < b) {
            res = 2;
        } else {
            res = 0;
        }
        System.out.println(res);*/

        /**
         * Дано натуральное число. Требуется определить,
         * является ли год с данным номером високосным.
         * Если год является високосным, то выведите YES,
         * иначе выведите NO. Напомним, что в соответствии с григорианским календарем,
         * год является високосным, если его номер кратен 4,
         * и не кратен 100, или же если он кратен 400.
         */
        /*Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            System.out.println("YES");
        } else {
            System.out.println("No");
        }*/

        /**
         * Даны три целых числа.
         * Найдите наибольшее из них (программа должна вывести ровно одно целое число).
         * Использовать не более 2-х раз оператор >,
         * другие логические операторы использовать в этой задаче запрещено
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int res = a;
        if(a < b){
            res = b;
        }
        if(a < c){
            res = c;
        }
        System.out.println(res);*/

        /**
         * Даны три целых числа.
         * Определите, сколько среди них совпадающих.
         * Программа должна вывести одно из чисел:
         * 3 (если все совпадают), 2 (если два совпадает) или 0 (если все числа различны).
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int res;
        if(a == b && a == c) {
            res = 3;
        } else if (a == b || b == c || a == c) {
            res = 2;
        } else {
            res = 1;
        }
        System.out.println(res);*/

        /**
         * Для данного числа n<100 закончите фразу “На лугу пасется...”
         * одним из возможных продолжений: “n коров”, “n корова”, “n коровы”,
         * правильно склоняя слово “корова”.
         * Программа должна вывести введенное число n и одно из слов:
         * korov, korova или korovy. Между числом и словом должен стоять ровно один пробел.
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n%10==1 && n!=11) {
            System.out.println("На лугу пасется " + n +" корова");
        } else if (n% 10 >= 2 && n % 10 <= 4 && !( n >= 12 && n <= 14))  {
            System.out.println("На лугу пасется " + n +" коровы");
        } else {
            System.out.println("На лугу пасется " + n +" коров");
        }*/

        /**
         * За многие годы заточения узник замка Иф проделал в стене прямоугольное отверстие размером D×E.
         * Замок Иф сложен из кирпичей, размером A×B×C.
         * Определите, сможет ли узник выбрасывать кирпичи в море через это отверстие,
         * если стороны кирпича должны быть параллельны сторонам отверстия.
         * Программа получает на вход числа A, B, C, D, E и должна вывести слово YES или NO
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int d = scanner.nextInt();
        int e = scanner.nextInt();
        if (a <= d && b <= e || a <= e && b <= d || c <= d && b <= e
                || c <= e && b <= d || c <= d && a <= e || c <= e && a <= d) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }*/

        /**
         * Есть две коробки, первая размером A1×B1×C1, вторая размером A2×B2×C2.
         * Определите, можно ли разместить одну из этих коробок внутри другой,
         * при условии, что поворачивать коробки можно только на 90 градусов вокруг ребер.
         * Программа получает на вход числа A1, B1, C1, A2, B2, C2.
         * Программа должна вывести одну из следующих строчек:
         * Boxes are equal, если коробки одинаковые,
         * The first box is smaller than the second one,
         * если первая коробка может быть положена во вторую,
         * The first box is larger than the second one,
         * если вторая коробка может быть положена в первую,
         * Boxes are incomparable, во всех остальных случаях.
         */
        /*Scanner scanner = new Scanner(System.in);
        int a1 = scanner.nextInt();
        int b1 = scanner.nextInt();
        int c1 = scanner.nextInt();
        int a2 = scanner.nextInt();
        int b2 = scanner.nextInt();
        int c2 = scanner.nextInt();
        if ((a1==a2 && b1==b2 && c1==c2) || (a1==a2 && b1==c2 && c1==b2)
                || (a1==b2 && b1==a2 && c1==c2) || (a1==b2 && b1==c2 && c1==a2) ||
                (a1==c2 && b1==b2 && c1==a2) || (a1==c2 && b1==a2 && c1==b2)){
            System.out.println("Boxes are equal");
        } else if  ((a1>=a2 && b1>=b2 && c1>=c2) || (a1>=a2 && b1>=c2 && c1>=b2)
                || (a1>=b2 && b1>=a2 && c1>=c2) || (a1>=b2 && b1>=c2 && c1>=a2) ||
                (a1>=c2 && b1>=b2 && c1>=a2) || (a1>=c2 && b1>=a2 && c1>=b2)) {
            System.out.println("The first box is larger than the second one");
        } else if  ((a1<=a2 && b1<=b2 && c1<=c2) || (a1<=a2 && b1<=c2 && c1<=b2)
                || (a1<=b2 && b1<=a2 && c1<=c2) || (a1<=b2 && b1<=c2 && c1<=a2) ||
                (a1<=c2 && b1<=b2 && c1<=a2) || (a1<=c2 && b1<=a2 && c1<=b2)) {
            System.out.println("The first box is smaller than the second one");
        } else {
            System.out.println("Boxes are incomparable");
        }*/

        /**
         * За день машина проезжает n километров.
         * Сколько дней нужно, чтобы проехать маршут длиной m километров?
         * Программа получает на вход числа n и m.
         */
        /*Scanner scanner = new Scanner(System.in);
        int auto = scanner.nextInt();
        int km = scanner.nextInt();
        int res = 0;
        if (auto % km > 0) {
            res =  km /auto + 1;
        }
        if (km % auto == 0) {
            res = km /auto;
        }
        System.out.println(res);*/

        /**
         * Дано четырехзначное число.
         * Определите, является ли его десятичная запись симметричной.
         * Вывести да или нет
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int a1 = a / 1000;
        int a2 = a / 100 % 10;
        if (a2 * 10 + a1 == a % 100) {
            System.out.println("Да");
        } else {
            System.out.println("Нет");
        }*/

        /**
         * Даны два натуральных числа n и m.
         * Если одно из них делится на другое нацело,
         * выведите да, иначе выведите нет
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (a % b == 0 || b % a == 0) {
            System.out.println("Да");
        } else {
            System.out.println("Нет");
        }*/
    }
}