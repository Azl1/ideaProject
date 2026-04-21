package com.repository;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.model.Galaxy;
import com.model.Planet;
import com.model.Universe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UniverseRepository {

    private Universe universe = new Universe();

    public UniverseRepository() {
    }

    public UniverseRepository(Universe universe) {
        this.universe = universe;
    }

    public UniverseRepository(String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        this.universe = xmlMapper.readValue(new File(fileName), Universe.class);
    }

    public Universe getUniverse() {
        return universe;
    }

    public void add(Galaxy galaxy){
        this.universe.add(galaxy);
    }

    public void save(String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlMapper.writeValue(bufferedWriter, this.universe);
        }
    }

    @Override
    public String toString() {
        return  "" + universe +
                '}';
    }
}
