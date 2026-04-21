package com.kirillkotov.model;

import java.util.Objects;

public abstract class Mammal {
    private String name;
    private String color;
    private int age;
    private double weight;

    public Mammal(String name, String color, int age, double weight) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.weight = weight;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public abstract void voice();

    /*public void voice(){
        System.out.println("iiii");
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mammal mammal = (Mammal) o;
        return age == mammal.age && Double.compare(mammal.weight, weight) == 0 && Objects.equals(name, mammal.name)
                && Objects.equals(color, mammal.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, age, weight);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
