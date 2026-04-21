package com.repository;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.model.Galaxy;
import com.model.Planet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GalaxyRepository {

    private Galaxy galaxy = new Galaxy();

    public GalaxyRepository() {
    }

    public GalaxyRepository(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public GalaxyRepository(String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        this.galaxy = xmlMapper.readValue(new File(fileName), Galaxy.class);
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void add(Planet planet){
        this.galaxy.add(planet);
    }

    public void save(String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlMapper.writeValue(bufferedWriter, this.galaxy);
        }
    }

    @Override
    public String toString() {
        return
                "" + galaxy +
                '}';
    }
}
