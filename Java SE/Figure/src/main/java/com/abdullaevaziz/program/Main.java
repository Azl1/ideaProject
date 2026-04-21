package com.abdullaevaziz.program;

import com.abdullaevaziz.model.*;
import com.abdullaevaziz.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class  Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Figure> figures = new ArrayList<>();
        HashSet<Figure> hashSet = new HashSet<>();

        /**
         * В методе main класса Program создать два экземпляра калькулятора
         * с передачей разных фигур, произвести вычисление площади и периметра данных фигур
         */
        Triangle triangle1 = new Triangle(5,7,8);
        Triangle triangle2 = new Triangle(8,10,7);
        Rectangle rectangle1 = new Rectangle(5,5);
        Rectangle rectangle2 = new Rectangle(10,12);
        Calculator calculator1 = new Calculator(triangle1);
        Calculator calculator2 = new Calculator(rectangle1);
        Repository repository = new Repository();
        figures.add(triangle2);
        figures.add(triangle1);
        figures.add(rectangle1);
        figures.add(rectangle2);



        try {
            repository.save("Input.txt", figures);

            repository.replacement("Input.txt", triangle1, rectangle1);
            System.out.println("replacement");

        } catch (IOException e) {
            e.getMessage();
        }


        double res1 = calculator1.calculate(Functor.PERIMETR);
        double res2 = calculator2.calculate(Functor.SQUARE);
        System.out.println(res1);
        System.out.println(res2);


        /**
         * Реализацию интерфейса Figure выполнить в виде анонимного внутреннего класса
         * и передать ее в объект калькулятора.
         * У анонимного внутреннего класса должно быть поле R – радиус окружности,
         * а так же сеттер, производящий его инициализацию.
         * При вызове сеттера данные передаются с клавиатуры. Вычислить и вывести на экран периметр
         * и площадь окружности с заданным радиусом
         */

        Figure iterFigure = new Figure() {
            double r;

            {
                r = scanner.nextDouble();
            }

            public void setR(double r) {
                this.r = r;
            }

            /**
             *Площадь
             */
            @Override
            public double square() {
                return Math.PI * this.r * this.r;
            }

            /**
             * Периметр
             */
            @Override
            public double perimeter() {
                return 2 * Math.PI * this.r;
            }

            @Override
            public String getName() {
                return Figure.super.getName();
            }

            @Override
            public String toCSV() {
                return this.getName() + ";" + this.r + ";" + this.square() + ";" + this.perimeter();
            }
        };
        double res3 = iterFigure.perimeter();
        double res4 = iterFigure.square();
        calculator1.calculate(Functor.PERIMETR);
        calculator1.calculate(Functor.SQUARE);
        System.out.println(res3);
        System.out.println(res4);

        Figure res5 = repository.maxFigure(hashSet, Functor.SQUARE);
        Figure res6 = repository.maxFigure(hashSet, Functor.PERIMETR);
        System.out.println(res5);
        System.out.println(res6);

        String printCSV = repository.toString();
        System.out.println(printCSV);


    }
}