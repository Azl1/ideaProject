package com.abdullaevaziz.model;

import java.util.Objects;

public class Plant implements Cloneable{

   private String name;
   private String color;
   private int countStems;
   private int lifetime;
   private int price;

   public Size size = new Size();

    public Plant() {
    }

    public Plant(String name, String color, int countStems, int lifetime, int price, Size size) {
        this.name = name;
        this.color = color;
        this.countStems = countStems;
        this.lifetime = lifetime;
        this.price = price;
        this.size = size;
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

    public int getCountStems() {
        return countStems;
    }

    public void setCountStems(int countStems) {
        this.countStems = countStems;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return countStems == plant.countStems && lifetime == plant.lifetime && price == plant.price && Objects.equals(name, plant.name) && Objects.equals(color, plant.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, countStems, lifetime, price);
    }

    /**
     * Метод увеличения поля (любого)
     * растения на заданную величину в аргументах
     */
    public int plantEnlargement(int value ) {
        this.countStems+= value;
        return this.countStems;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", countStems=" + countStems +
                ", lifetime=" + lifetime +
                ", price=" + price +
                '}';
    }

    @Override
    public Plant clone() {
        try {
            Plant clone = (Plant) super.clone();
            clone.size = this.size.clone();

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
