package com.abdullaevaziz;

import com.model.Galaxy;
import com.model.Planet;
import com.model.Universe;
import com.repository.GalaxyRepository;
import com.repository.PlanetRepository;
import com.repository.UniverseRepository;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

   /*     Planet planet1 = new Planet("Юпитер", 2500, 8000);
        Planet planet2 = new Planet("Марс", 1500, 7000);
        Planet planet3 = new Planet("Нептун", 3000, 5000);

        Planet planetColor1 = new Planet("Серая планета", 250440, 807700);
        Planet planetColor2 = new Planet("Желтая планет", 222500, 700880);
        Planet planetColor3 = new Planet("Зеленая планета", 300560, 599000);

        Planet planetUnknown1 = new Planet("Неизвестная планета 1", 25048740, 800540);
        Planet planetUnknown2 = new Planet("Неизвестная планета 2", 1232500, 705400);
        Planet planetUnknown3 = new Planet("Неизвестная планета 3", 303200, 50232300);

        Galaxy galaxy = new Galaxy("Млечный путь");
        Galaxy galaxyAndromed = new Galaxy("Андромед");
        galaxyAndromed.add(planetColor1);
        galaxyAndromed.add(planetColor2);
        galaxyAndromed.add(planetColor3);

        Galaxy galaxyUnknown = new Galaxy("Неизвестная галактика");
        galaxyUnknown.add(planetUnknown1);
        galaxyUnknown.add(planetUnknown2);
        galaxyUnknown.add(planetUnknown3);

        Universe universe = new Universe();

        System.out.println("Название галактики млечный путь");
        System.out.println();
        System.out.println("Планета и их радиус\n" + planet2);
        System.out.println("Планета и скорость вращения \n" + planet2.behavior());

        boolean addPlanet = galaxy.add(planet3);
        System.out.println(addPlanet);
        boolean addPlanet1 = galaxy.add(planet2);
        boolean addPlanet2 = galaxy.add(planet1);
        boolean addPlanet3 = galaxy.add(planet3);

        System.out.println(addPlanet1);
        System.out.println(addPlanet2);
        System.out.println(addPlanet3);
        System.out.println();
        System.out.println("Поиск планеты " );
        Planet planetSearch = galaxy.search("Нептун");
        System.out.println(planetSearch);
        int indexPlanet = galaxy.search(planet3);
        System.out.println(indexPlanet);
        System.out.println();

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

        GalaxyRepository galaxyRepository = new GalaxyRepository();
        galaxyRepository.add(new Planet("Юпитер", 50, 100));
        galaxyRepository.add(new Planet("Марс", 1500, 7000));
        galaxyRepository.add(new Planet("Нептун", 2500, 8500));
        galaxyRepository.add(new Planet("Сатурн", 4500, 7040));
        galaxyRepository.save("galaxy.xml");

        PlanetRepository planetRepository = new PlanetRepository(new Planet("Марс", 1500, 7000));
        planetRepository.save("planet.xml");

        UniverseRepository universeRepository = new UniverseRepository();
        universeRepository.add(galaxy);
        universeRepository.add(galaxyAndromed);
        universeRepository.add(galaxyUnknown);
        universeRepository.save("universe.xml");*/

        GalaxyRepository galaxyRepository = new GalaxyRepository("galaxy.xml");
        Galaxy galaxy = galaxyRepository.getGalaxy();
        System.out.println(galaxy);
        System.out.println();

        PlanetRepository planetRepository = new PlanetRepository("planet.xml");
        Planet planet = planetRepository.getPlanet();
        System.out.println(planet);
        System.out.println();

        UniverseRepository universeRepository = new UniverseRepository("universe.xml");
        System.out.println();
        Universe universe = universeRepository.getUniverse();
        System.out.println(universe);

    }

}