package com.abdullaevaziz.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * класс Point в пакете model, описывающий точку в системе координат XOY
 */
public class Point {
    private double x;
    private double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * сложения точек
     *
     * @param other
     * @return
     */
    public Point add(Point other) {
        return new Point(this.x + other.x, this.y + other.y);
    }

    /**
     * умножения точки на число
     *
     * @param val
     * @return
     */
    public Point mult(double val) {
        return new Point(this.x * val, this.y * val);
    }

    /**
     * деления точки на число
     */
    public Point division(double val) {
        return new Point(this.x / val, this.y / val);
    }

    /**
     * Mетод dist,
     * который возвращает расстояние от точки до начала координат
     */
    public double dist() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Самая дальняя точка(maxDistPoint)
     * Вычислить точку, наиболее удаленную от начала координат.
     * Для решения этой задачи напишите и используйте дополнительный метод dist,
     * который возвращает расстояние от точки до начала координат.
     * Если таких точек несколько, то вернуть последнюю, удовлетворяющую условиям задачи
     */
    public static Point maxDistPoint(Point[] points) {
        Point res = new Point();
        for (int i = 0; i < points.length; i++) {
            if (points[i].dist() > res.dist()) {
                res = points[i];
            }
        }
        return res;
    }

    /**
     * A1: Самые дальние точки(maxDistPoints)
     * Вычислить точки, наиболее удаленные от начала координат.
     */
    public static ArrayList<Point> maxDistPoints(Point[] points) {
        ArrayList<Point> resList = new ArrayList<>();
        Point temp = maxDistPoint(points);
        for (int i = 0; i < points.length; i++) {
            if (points[i].dist() == temp.dist()) {
                resList.add(temp);
            }
        }
        return resList;
    }

    /**
     * B: Центр масс(centerMass)
     * Вычислите точку, являющуюся центом масс данного множества точек,
     * т.е. точку, которая имеет в качестве своих координат среднее арифметическое по х и у.
     */
    public static Point centerMass(Point[] points) {
        Point res = new Point();
        for (int i = 0; i < points.length; i++) {
            res = res.add(points[i]);
        }
        return res.division(points.length);
    }

    /**
     * C: Диаметр множества(setDiam)
     * Выведите диаметр данного множества – максимальное расстояние между двумя данными точками.
     * Для решения этой задачи реализуйте и используйте перегрузку метода dist,
     * который принимает на вход вторую точку и возвращает расстояние между двумя данными точками.
     */
    public double dist(Point point) {
        return Math.sqrt((point.x - this.x) + (point.y - this.y) * 2);
    }

    public static double setDiam(Point[] points) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dist = points[i].dist(points[j]);
                if (dist > max) {
                    max = dist;
                }
            }
        }
        return max;
    }

    /**
     * E: Максимальный периметр(maxPerimetr)
     * Среди данных точек найдите три точки, образующие треугольник с наибольшим периметром.
     * Выведите данный периметр.
     * Для нахождения периметра треугольника напишите отдельный метод perimeter,
     * возвращающий периметр между тремя данными точками.
     */
    public double perimeter(Point o1, Point o2) {
        double a = this.dist(o1);
        double b = this.dist(o2);
        double c = o1.dist(o2);
        return (a + b + c);
    }

    public static double maxPerimeter(Point[] points) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    double perimeterMax = points[i].perimeter(points[j], points[k]);
                    if (perimeterMax > max) {
                        max = perimeterMax;
                    }
                }
            }
        }
        return max;
    }

    /**
     * F: Максимальная площадь(maxArea)
     * Среди данных точек найдите три точки, образующие треугольник с наибольшей площадью.
     * Выведите данную площадь.
     * Для нахождения площади треугольника напишите отдельный метод area.
     */

    public double area (Point o1, Point o2){
        double a = this.dist(o1);
        double b = this.dist(o2);
        double c = o1.dist(o2);
        double p = (a * b * c) / 2;
        return Math.sqrt(p * (p - a) * (p-b) * (p-c));
    }

    public static double maxArea(Point[] points) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    double maxSquare = points[i].area(points[j], points[k]);
                    if (maxSquare > max) {
                        max = maxSquare;
                    }
                }
            }
        }
        return max;
    }
}
