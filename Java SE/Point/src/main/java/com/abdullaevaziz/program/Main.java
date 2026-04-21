package com.abdullaevaziz.program;

import com.abdullaevaziz.model.Point;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Point point = new Point();
        Point point1 = new Point(12.2, 10);
        Point point2 = new Point(16, 30);

        Point add = point1.add(point1);
        System.out.println(add);

        Point mult = point1.mult(6);
        System.out.println(mult);
        Point divis = point1.division(8);
        System.out.println(divis);

        System.out.println();
        Point[] points1 = {new Point(2, 3), new Point(7, 8), new Point(10, 20)};
        Point pointsMass = Point.maxDistPoint(points1);
        System.out.println(pointsMass);

        System.out.println();
        Point[] points2 = {new Point(2, 3), new Point(7, 8), new Point(10, 20)};
        ArrayList<Point> pointsList = Point.maxDistPoints(points2);
        System.out.println(pointsList);

        System.out.println("Метод dist 1" );
        double redDist = point1.dist();
        System.out.println(redDist);
        System.out.println("Центр масс");
        Point centerMass1 = Point.centerMass(points1);
        System.out.println(centerMass1);
        System.out.println();

        System.out.println("Метод dist 2" );
        double redDistPoints = point1.dist(point2);
        System.out.println(redDistPoints);
        System.out.println("Диаметр множества");
        double setDiam1 = Point.setDiam(points1);
        System.out.println(setDiam1);
        System.out.println();

        System.out.println("Максимальный периметр");
        double maxPerimeter1 = Point.maxPerimeter(points2);
        System.out.println(maxPerimeter1);
        System.out.println();

        System.out.println("Максимальная площадь");
        double maxArea1 = Point.maxArea(points2);
        System.out.println(maxArea1);

    }
}