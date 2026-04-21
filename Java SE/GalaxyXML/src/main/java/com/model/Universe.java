package com.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;

public class Universe {

    @JacksonXmlElementWrapper(localName = "list")
    @JacksonXmlProperty(localName = "galaxy")
    private ArrayList<Galaxy> galaxies = new ArrayList<>();

    /**
     * Во вселенной реализовать метод добавления
     * новой галактики в список всех галактик
     */
    public void add(Galaxy galaxy) {
        this.galaxies.add(galaxy);
    }

    /***
     * поиск планеты из вселенной по имени (возвращает объект)
     */
    public Planet searchPlanet(String name) {
        for (Galaxy galaxy : this.galaxies) {
            Planet search = galaxy.search(name);
            if (search != null) {
                return search;
            }
        }
        return null;
    }

    /**
     * поиск по объекту (возвращает массив из двух индексов:
     * индекс галактики во вселенной и индекс планеты в найденной галактике)
     */
    public int[] search(Planet planet) {
        for (int i = 0; i < this.galaxies.size(); i++) {
            Galaxy galaxy = this.galaxies.get(i);
            int ind = galaxy.search(planet);
            if (ind != -1) {
                return new int[]{i, ind};
            }
        }
        return new int[0];
    }

    /**
     * метод поиска галактики из вселенной (по объекту).
     */
    public int search(Galaxy galaxy) {
        return galaxies.indexOf(galaxy);
    }

    /**
     * метод поиска галактики из вселенной (по имени).
     */
    public Galaxy search(String name) {
        for (Galaxy galaxy : this.galaxies) {
            if (galaxy.getName().equals(name)) {
                return galaxy;
            }
        }
        return null;
    }

    /**
     * Метод удаление по объекту
     */
    public boolean remove(Galaxy galaxy) {
        return galaxies.remove(galaxy);
    }

    /**
     * Метод удаление по имени
     */
    public Galaxy remove(String name) {
        Galaxy galaxyRemoveName = this.search(name);
        this.galaxies.remove(galaxyRemoveName);
        return galaxyRemoveName;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Universe{"
                + galaxies +
                '}';
    }
}
