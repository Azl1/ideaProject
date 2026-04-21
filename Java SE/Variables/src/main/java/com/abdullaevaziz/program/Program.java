package com.abdullaevaziz.program;

import java.util.Scanner;

public class Program {

    /**
     * Задание по теме «Переменные»
     * 1.	В среде разработки IntelliJ Idea создать новый maven проект с названием Variables
     * 2.	Проверить, чтобы структура проекта была следующая: com.вашНик.program.Program. Если нет, то создать
     * 3.	Проверить, есть ли в классе запускающий метод main. Если нет, то создать
     * 4.	Документируемым комментарием в методе main показать данный текст задания
     * 5.	Создать переменные всех встроенных в Java типов данных, кроме char, и произвести их инициализацию, используя Scanner
     * 6.	После объявления и инициализации всех переменных вывести на экран их значения через пробел
     * 7.	Объявить две константные переменные, проинициализировав их не со Scanner, а сразу же, в коде
     * 8.	Проверить, что константную переменную нельзя изменить после ее инициализации
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner scanner = new Scanner(System.in);
        String k = scanner.nextLine();
        long a = scanner.nextLong();
        int b = scanner.nextInt();
        short c = scanner.nextShort();
        byte d = scanner.nextByte();

        double e = scanner.nextDouble();
        float f = scanner.nextFloat();
        boolean h = scanner.hasNextBoolean();

        System.out.println(k + " " + a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + h);
    }
}

