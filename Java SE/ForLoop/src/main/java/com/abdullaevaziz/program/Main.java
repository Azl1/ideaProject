package com.abdullaevaziz.program;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
         * Даны два целых числа A и B (при этом A≤B).
         * Выведите все числа от A до B включительно,
         * разделяя их пробелом. После последнего числа тоже можно вывести пробел.
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        for (int i = a; i <= b; i++) {
            System.out.print(i + " ");
        }*/

        /**
         * Даны два целых числа A и В.
         * Выведите все числа от A до B включительно,
         * в порядке возрастания, если A < B, или в порядке убывания в противном случае.
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (a < b) {
            for (int i = a; i <= b; i++) {
                System.out.println(i + " ");
            }
        } else {
            for (int i = a; i >= b; i--) {
                System.out.println(i + " ");
            }
        }*/

        /**
         * Дано число n.
         * Посчитать значение суммы всех чисел от 1 до n.
         * Использовать алгоритм накопления суммированием
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int p = 1;
        for (int i = 1; i <= n; i++) {
            p += i;
        }
        System.out.println(p + " ");*/

        /**
         * Даны числа a, b (при этом A≤B).
         * Посчитать значение произведение всех чисел от a до b.
         * Использовать алгоритм накопления умножением
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int p = 1;
        for (int i = a; i <= b; i++) {
            p *= i;
        }
        System.out.println(p + " ");*/

        /**
         * По данному натуральном n вычислите сумму 13+23+33+...+n3.
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i * i * i;
        }
        System.out.println(sum + " ");*/

        /**
         * По данному натуральному n вычислите сумму 1×2+2×3+...+(n-1)×n.
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        for (int i = 1; i < n; i++) {
            sum += i * (i + 1);
        }
        System.out.println(sum + " ");*/

        /**
         * По данному целому неотрицательному n вычислите значение n!
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
        }
        System.out.println(sum + " ");*/

        /**
         * По данным двум натуральным числам
         * A и B (A≤B) выведите все чётные числа на отрезке от A до B.
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        for (int i = a; i <= b; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }*/

        /**
         * По данному натуральному числу
         * n≤1000 выведите все натуральные делители числа n в порядке возрастания.
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 1 ; i <= n; i++){
            if(n % i == 0 ) {
                System.out.print(i + " ");
            }
        }*/

        /**
         * Дано 10 целых чисел.
         * Вычислите их сумму. В решении можно использовать только три переменные.
         */
        /*Scanner scanner = new Scanner(System.in);
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            int num = scanner.nextInt();
            sum += num;
        }
        System.out.println(sum);*/

        /**
         * Дано несколько чисел.
         * Вычислите их сумму. Сначала вводите количество чисел N,
         * затем вводится ровно N целых чисел.
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int num = scanner.nextInt();
            sum += num;
        }
        System.out.println(sum);*/

        /**
         * Дано N чисел:
         * сначала вводится число N, затем вводится ровно N целых чисел.
         * Подсчитайте количество нулей среди введенных чисел и выведите это количество
         */
        /*Scanner scanner = new Scanner(System.in);
        int n  = scanner.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            if (num == 0) {
                sum++;
            }
        }
        System.out.println(sum);*/

        /**
         * Замечательные числа - 1
         * Найдите и выведите все двузначные числа, которые равны удвоенному произведению своих цифр.
         * Программа не требует ввода данных с клавиатуры, просто выводит список искомых чисел.
         */
        /*for (int i = 10; i <= 99; i++) {
            int i1 = i / 10;
            int i2 = i % 10;
            if(2 * i1 * i2 == i){
                System.out.println(i);
            }
        }*/

        /**
         * Квадрат трехзначного числа оканчивается тремя цифрами,
         * равными этому числу. Найдите и выведите все такие числа.
         * Программа не требует ввода данных с клавиатуры,
         * просто выводит список искомых чисел.
         */
        /*for (int i = 100; i <= 999; i++) {
            int t = i * i; //получаем квадрат числа
            int k = t % 1000; //берем его 3 последние цифры
            if (k == i){
                System.out.println(i);
            }
        }*/

        /**
         * Дано натуральное число n.
         * Выведите в порядке возрастания все трехзначные числа,
         * сумма цифр которых равна n.
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 100; i <= 999; i++) {
            int i1 = i / 100 % 10 ;
            int i2 = i / 10 % 10;
            int i3 = i % 10 ;
            if (i1 + i2 + i3 == n ) {
                System.out.println(i);
            }
        }*/

        /**
         * Даны числа а и b.
         * Найти количество и произведение всех нечетных чисел на данном отрезке
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int p = 1;
        int sum = 0;
        for (int i = a; i <= b; i++) {
            if (i % 2 != 0) {
                sum += i;
                p *= i;
            }
        }
        System.out.println(sum);
        System.out.println(p);*/

       /**
         * Замечательные числа - 4
         *
         * Даны два четырёхзначных числа A и B.
         * Выведите все четырёхзначные числа на отрезке от A до B,
         * запись которых является палиндромом.
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        for (int i = a; i <= b; i++) {
            int i1 = i / 1000 % 10;
            int i2 = i / 100 % 10;
            int i3 = i / 10 % 10;
            int i4 = i % 10;
            if (i1 == i4 && i2 == i3) {
                System.out.println(i);
            }
        }*/

        /**
         *  Даны два четырёхзначных числа A и B.
         *  Выведите в порядке возрастания все четырёхзначные
         *  числа на отрезке от A до B,
         *  запись которых содержит ровно три одинаковые цифры.
         */
        /*Scanner scanner = new Scanner(System.in);
        int a  = scanner.nextInt();
        int b  = scanner.nextInt();
        for (int i = a; i < b ; i++) {
           int i1 = i / 1000;
           int i2 = i / 100 % 10;
           int i3 = i / 10 % 10;
           int i4 = i % 10;
            if ((i1 == i2) && (i2 == i3) && (i3 != i4) ||
                    (i1 == i2) && (i2 == i4) && (i4 != i3) ||
                    (i1 == i3) && (i3 == i4) && (i4 != i2) ||
                    (i2 == i3) && (i3 == i4) && (i4 != i1) ){
                System.out.println(i);
            }
        }*/

        /**
         * W: Сумма факториалов
         *
         * По данному натуральном n
         * вычислите сумму 1!+2!+3!+...+n!.
         * В решении этой задачи можно использовать только один цикл.
         */
        /*Scanner scanner = new Scanner(System.in);
        int n  = scanner.nextInt();
        int suma = 1;
        int p = 1;
        for (int i = 2; i <= n ; i++) {
            p *= i;
            suma += p;
        }
        System.out.println(suma);*/

        /**
         * Z: Остатки
         * Даны целые неотрицательные числа a, b, c, d.
         * Выведите в порядке возрастания все числа от a до b,
         * которые дают остаток c при делении на d.
         */
        /*Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int d = scanner.nextInt();
        for (int i = a; i <= b ; i++) {
            if(i % d == c ){
                System.out.println(i);
            }
        }*/

        /**
         * Z1: Простое число
         *
         * Дано число n.
         * Определить, является ли оно простым.
         * Простое число – число, имеющее ровно 2 делителя: оно само и единица
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                count++;
            }
        }
        if(count == 2){
            System.out.println("Да");
        }
        else{
            System.out.println("Нет");
        }*/


        /**
         * Z2: Совершенное число
         *
         * Дано число n. Определить, является ли оно совершенным.
         * Совершенное число – число, равное сумме своих делителей без последнего числа
         */
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }

        if(sum == n){
            System.out.println("Да");
        }
        else{
            Systefm.out.println("Нет");
        }*/
    }
}