package com.kirillkotov.model;

import java.util.Objects;

public class Cat extends Mammal{
    private int mouseExperience;

    public Cat(String name, String color, int age, double weight, int mouseExperience) {
        super(name, color, age, weight);
        this.mouseExperience = mouseExperience;
    }

    public int getMouseExperience() {
        return mouseExperience;
    }

    public void setMouseExperience(int mouseExperience) {
        this.mouseExperience = mouseExperience;
    }

    @Override
    public void voice() {
        System.out.println("myau");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cat cat = (Cat) o;
        return mouseExperience == cat.mouseExperience;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mouseExperience);
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "");

    }
}
