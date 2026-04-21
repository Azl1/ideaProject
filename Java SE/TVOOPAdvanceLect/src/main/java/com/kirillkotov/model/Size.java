package com.kirillkotov.model;

import java.util.Objects;

public class Size {
    private double length;
    private double width;
    private double height;

    private double diagonal;

    public Size() {
    }

    public Size(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.setDiagonal();
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
        this.setDiagonal();
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        this.setDiagonal();
    }

    public double getDiagonal() {
        return diagonal;
    }

    private void setDiagonal(){
        this.diagonal = Math.hypot(this.length, this.height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return Double.compare(size.length, length) == 0 && Double.compare(size.width, width) == 0
                && Double.compare(size.height, height) == 0 && Double.compare(size.diagonal, diagonal) == 0;
    }


    @Override
    public int hashCode() {
        return Objects.hash(length, width, height, diagonal);
    }

    @Override
    public String toString() {
        return "Size{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", diagonal=" + diagonal +
                '}';
    }
}
