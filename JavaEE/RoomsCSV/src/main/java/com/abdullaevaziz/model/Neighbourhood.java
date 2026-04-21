package com.abdullaevaziz.model;

import java.util.Objects;

public class Neighbourhood {

    private String name;
    private String borough;

    public Neighbourhood() {
    }

    public Neighbourhood(String name, String borough) {
        this.name = name;
        this.borough = borough;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbourhood that = (Neighbourhood) o;
        return Objects.equals(name, that.name) && Objects.equals(borough, that.borough);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, borough);
    }

    @Override
    public String toString() {
        return "Neighbourhood{" +
                "name='" + name + '\'' +
                ", borough='" + borough + '\'' +
                '}';
    }
}
