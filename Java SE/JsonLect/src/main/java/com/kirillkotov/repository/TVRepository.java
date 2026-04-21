package com.kirillkotov.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kirillkotov.model.TV;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Kotov Kirill
 * Add dependencies from following links for working with class
 * https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
 * https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310
 */
public class TVRepository {
    private ArrayList<TV> tvs = new ArrayList<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        //TODO для работы с LocalDateTime
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public TVRepository() {
    }

    /**
     * Read ArrayList of TV using BufferedReader
     * @param fileName Path of json file
     * @throws IOException
     */
    public TVRepository(String fileName) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            this.tvs = objectMapper.readValue(bufferedReader, new TypeReference<>() {});
        }
    }

    public ArrayList<TV> getTvs() {
        return tvs;
    }

    public void add(TV tv){
        this.tvs.add(tv);
    }

    /**
     * Save ArrayList of TV using BufferedWriter
     * @param fileName Path of json file
     * @throws IOException
     */
    public void save(String fileName) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))){
            objectMapper.writeValue(bufferedWriter, this.tvs);
            /*String s = objectMapper.writeValueAsString(this.tvs);
            bufferedWriter.write(s);*/
        }
    }

    @Override
    public String toString() {
        return "TVRepository{" +
                "tvs=" + tvs +
                '}';
    }
}
