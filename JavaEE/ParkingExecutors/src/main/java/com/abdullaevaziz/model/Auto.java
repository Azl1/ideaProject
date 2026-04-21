package com.abdullaevaziz.model;

import java.util.Objects;

public class Auto {
    private static int ID = 1;
    private int id = ID++;
    private int type;

    public Auto() {
    }

    public Auto(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return id == auto.id && type == auto.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
