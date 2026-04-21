package com.abdullaevaziz.program.program;

import com.abdullaevaziz.program.model.Galaxy;
import com.abdullaevaziz.program.model.Planet;
import com.abdullaevaziz.program.model.Universe;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        Planet planet = new Planet();
        Planet planet1 = new Planet("Юпитер", 2500, 8000);
        Planet planet2 = new Planet("Марс", 1500, 7000);
        Planet planet3 = new Planet("Нептун", 3000, 5000);

        Galaxy galaxy = new Galaxy("Млечный путь");
        Galaxy galaxyAndromed = new Galaxy("Андромед");

        Universe universe = new Universe();



        System.out.println("Название галактики млечный путь");
        System.out.println();
        System.out.println("Планета и их радиус\n" + planet2);
        System.out.println("Планета и скорость вращения \n" + planet2.behavior());

        boolean addPlanet = galaxy.add(planet3);
        System.out.println(addPlanet);
        boolean addPlanet1 = galaxy.add(planet2);
        boolean addPlanet2 = galaxy.add(planet1);
        System.out.println(addPlanet1);
        System.out.println(addPlanet2);
        System.out.println();
        System.out.println("Поиск планеты " );
        Planet planetSearch = galaxy.search("Нептун");
        System.out.println(planetSearch);
        int indexPlanet = galaxy.search(planet3);
        System.out.println(indexPlanet);
        System.out.println();

      /*System.out.println("Удаление планеты ");
        Planet removePlanet = galaxy.remove("Нептун");
        System.out.println(removePlanet);
        boolean isRemovePlanet = galaxy.remove(planet2);
        System.out.println(isRemovePlanet);*/

        System.out.println();
        universe.add(galaxy);
        universe.add(galaxyAndromed);

        Planet planetSearchName = universe.searchPlanet("Юпитер");
        System.out.println(planetSearchName);

        int[] indexPlanetUniverse = universe.search(planet2);
        System.out.println(Arrays.toString(indexPlanetUniverse));

        int indexGalactic = universe.search(galaxy);
        System.out.println(indexGalactic);

        Galaxy galaxyName = universe.search("Млечный путь");
        System.out.println(galaxyName);

        System.out.println();
        boolean removeNameGalaxy = universe.remove(galaxy);
        System.out.println(removeNameGalaxy);

        Galaxy galaxyNameRemove = universe.remove("Андромед");
        System.out.println(galaxyNameRemove);
    }
}
