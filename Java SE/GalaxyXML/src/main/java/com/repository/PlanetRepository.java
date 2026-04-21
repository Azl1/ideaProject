package com.repository;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.model.Planet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlanetRepository {

    private Planet planet = new Planet();

    public PlanetRepository() {
    }

    public PlanetRepository(Planet planet) {
        this.planet = planet;
    }

    public PlanetRepository(String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        this.planet = xmlMapper.readValue(new File(fileName), Planet.class);
    }

    public Planet getPlanet() {
        return planet;
    }


    public void save(String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlMapper.writeValue(bufferedWriter, this.planet);
        }
    }

    public void behaviorPlanet(Planet planet){
        this.planet.behavior();
    }

    @Override
    public String toString() {
        return  "Planets=" + planet +
                '}';
    }
}
