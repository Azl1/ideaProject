package com.abdullaevaziz.model;

import java.util.Objects;

public class Size implements Cloneable{
    private int length;
    private int height;
    private int plantPerimeter;

    public Size() {
    }

    public Size(int length, int height, int plantPerimeter) {
        this.length = length;
        this.height = height;
        this.plantPerimeter = plantPerimeter;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPlantPerimeter() {
        return plantPerimeter;
    }

    public void setPlantPerimeter(int plantPerimeter) {
        this.plantPerimeter = plantPerimeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return length == size.length && height == size.height && plantPerimeter == size.plantPerimeter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, height, plantPerimeter);
    }

    @Override
    public Size clone() {
        try {
            Size clone = (Size) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Size{" +
                "plant=" +
                '}';
    }

}
