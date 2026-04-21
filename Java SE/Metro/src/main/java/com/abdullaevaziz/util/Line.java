package com.abdullaevaziz.util;

import java.util.Objects;

public class Line {

    /**
     * Указание:
     * Разработать схему данных и коллекций(структур данных),
     * необходимых для рационального решения поставленных задач и
     * согласовать ее со своим руководителем проекта
     */
    private String name;
    private String color;

    public Line() {
    }

    public Line(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(name, line.name) && Objects.equals(color, line.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    @Override
    public String toString() {
        return "Line{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String toCSV() {
        return  this.name + ";" + this.color;
    }

}
