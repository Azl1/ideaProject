package com.model;

import java.util.Objects;

public class Planet {

    private String namePlanet;
    private int radius;
    private int period;

    public Planet() {
    }

    public Planet(String namePlanet, int radius, int period) {
        this.namePlanet = namePlanet;
        this.radius = radius;
        this.period = period;
    }

    public String getNamePlanet() {
        return namePlanet;
    }

    public void setNamePlanet(String namePlanet) {
        this.namePlanet = namePlanet;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    /**
     * Этот метод вычисляет скорость вращения вокруг своей оси
     * @return
     */
    public double speedCalculations() {
        return 2 * Math.PI * getPeriod() / getPeriod();
    }

    /**
     * возвращает строку,
     * содержащую имя планеты и скорость вращения вокруг своей оси
     * @return
     */
    public String behavior() {
        return this.namePlanet + " " + speedCalculations();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return radius == planet.radius && period == planet.period && Objects.equals(namePlanet, planet.namePlanet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namePlanet, radius, period);
    }

    @Override
    public String toString() {
        return
                "namePlanet='" + namePlanet + '\'' +
                ", radius=" + radius +
                ", appeals=" + period +
                '}';
    }
}
