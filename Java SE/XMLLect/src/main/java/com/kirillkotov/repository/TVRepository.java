package com.kirillkotov.repository;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kirillkotov.model.TV;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TVRepository {
    private TV tv;

    public TVRepository(TV tv) {
        this.tv = tv;
    }

    public TVRepository(String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        this.tv = xmlMapper.readValue(new File(fileName), TV.class);
    }

    public TV getTv() {
        return tv;
    }

    public void save(String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlMapper.writeValue(bufferedWriter, this.tv);
        }
    }

    @Override
    public String toString() {
        return "TVRepository{" +
                "tv=" + tv +
                '}';
    }
}
