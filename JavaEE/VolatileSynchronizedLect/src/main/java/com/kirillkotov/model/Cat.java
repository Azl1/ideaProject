package com.kirillkotov.model;

import java.util.Objects;

public class Cat {
    // Имя и количество жизней
    private String name;
    private volatile int life;

    public Cat() {
    }

    public Cat(String name, int life) {
        this.name = name;
        this.life = life;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public synchronized void decrementLife() {
        this.life--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return life == cat.life && Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, life);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", life=" + life +
                '}';
    }

    public boolean isAlive() {
        return this.life > 0;
    }
}
