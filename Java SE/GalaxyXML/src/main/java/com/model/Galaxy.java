package com.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.Objects;

public class Galaxy {

    private String name;
    @JacksonXmlElementWrapper(localName = "list")
    @JacksonXmlProperty(localName = "planet")
    private ArrayList<Planet> planets = new ArrayList<>();

    public Galaxy() {
    }

    public Galaxy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Добавляет планету
     * @param planet
     */
    public boolean add(Planet planet) {
        if(this.planets.contains(planet)){
            return false;
        }
        this.planets.add(planet);
        return true;
    }

    /**
     * поиск по объекту самой планеты
     * (возвращает индекс вхождения)
     * @param planet
     */
    public int search(Planet planet) {
        return planets.indexOf(planet);
    }

    /**
     * поиск планеты по ее имени в галактике
     * (возвращает объект планеты)
     * @param name
     */
    public Planet search(String name) {
        for (Planet planet : this.planets) {
            if(planet.getNamePlanet().equals(name)){
                return planet;
            }
        }
        return null;
    }

    /**
     * метод удаления планеты
     * из галактики по ее имени (возвращает объект)
     */
    public Planet remove(String name){
        Planet removePlanet = this.search(name);
        this.planets.remove(removePlanet);
        return removePlanet;
    }

    /**
     * метод удаления
     * по объекту самой планеты (возвращает boolean)
     */
    public boolean remove(Planet planet){
        return this.planets.remove(planet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Galaxy galaxy = (Galaxy) o;
        return Objects.equals(name, galaxy.name) && Objects.equals(planets, galaxy.planets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, planets);
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "name='" + name + '\'' +
                ", planets=" + planets +
                '}';
    }
}
