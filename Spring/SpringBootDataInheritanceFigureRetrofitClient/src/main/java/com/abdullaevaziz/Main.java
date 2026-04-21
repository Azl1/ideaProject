package com.abdullaevaziz;

import com.abdullaevaziz.model.*;
import com.abdullaevaziz.retrofit.FigureRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        FigureRepository figureRepository = new FigureRepository();

        Circle circle1 = new Circle(1);
        Circle circle2 = new Circle(2);

        Rectangle rectangle1 = new Rectangle(3);
        Rectangle rectangle2 = new Rectangle(4);

        Square square1 = new Square(5);
        ;

        Triangle triangle1 = new Triangle(7, 8, 9);

        try {
            List<Figure> list1 = figureRepository.getAll();
            System.out.println("\nAll figure from server:\n" + list1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        try {
            Figure figure1 = figureRepository.post(triangle1);
            System.out.println("figure1 is added to server!");
            System.out.println(figure1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Figure figureAddSquare = figureRepository.post(square1);
            System.out.println("Square is added to server!");
            System.out.println(figureAddSquare);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Figure figure2 = figureRepository.get(2);
            System.out.println("\nfigure2 by id 1 from server:\n" + figure2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        try {
            Triangle triangle2 = new Triangle(4L, 10, 11, 12);
            Figure figure3 = figureRepository.put(triangle2);
            System.out.println("This figure3  and Triangle1 is updated to server!");
            System.out.println(figure3);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


        try {
            Figure figure4 = figureRepository.delete(2);
            System.out.println("\nfigure4 by id 1 deleted from server:\n" + figure4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Square square2 = new Square(3L, 12);
            Figure figure5 = figureRepository.put(square2);
            System.out.println("This figure5  and square1 is updated to server!");
            System.out.println(figure5);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}