package com.abdullaevaziz.program;

import com.abdullaevaziz.model.*;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            Repository repository = new Repository();
            Figure figure1 = new Circle(5);
            Figure figure2 = new Rectangle(5);
            Figure figure10 = new Rectangle(10);
            Figure figure3 = new Square(5);
            Figure figure4 = new Triangle(6, 8, 4);

            repository.add(figure1);
            repository.add(figure2);
            repository.add(figure3);
            repository.add(figure4);
            repository.add(figure10);

            //repository.save("out.csv");
            repository.saveToJacksonFormat("out.json");

            Repository repository1 = new Repository("out.json");
            List<Figure> figureList = repository1.getData();
            System.out.println(figureList);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Для каждой фигуры из списка вызвать методы square() и perimeter().
     * Вернуть название фигуры, площадь и ее периметр
     */
    public static void f(Figure figure) {
        figure.square();
        figure.perimeter();
    }

}