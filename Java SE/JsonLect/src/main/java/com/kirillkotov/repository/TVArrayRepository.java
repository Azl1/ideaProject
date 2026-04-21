package com.kirillkotov.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kirillkotov.model.TV;

import java.io.*;
import java.util.Arrays;

/**
 * @author Kotov Kirill
 * Add dependencies from following links for working with class
 * https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
 * https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310
 */
public class TVArrayRepository {
    private TV[] tvs = new TV[0];

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        //TODO для работы с LocalDateTime
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public TVArrayRepository() {
    }

    /**
     * Read Array of TV using BufferedInputStream
     * @param fileName Path of json file
     * @throws IOException
     */
    public TVArrayRepository(String fileName) throws IOException {
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName))){
            this.tvs = objectMapper.readValue(bufferedInputStream, TV[].class);
        }
    }

    public TV[] getTvs() {
        return tvs;
    }

    public void add(TV tv){
        this.tvs = Arrays.copyOf(this.tvs, tvs.length + 1);
        this.tvs[this.tvs.length - 1] = tv;
    }

    /**
     * Save Array of TV using BufferedOutputStream
     * @param fileName Path of json file
     * @throws IOException
     */
    public void save(String fileName) throws IOException {
        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName))){
            objectMapper.writeValue(bufferedOutputStream, this.tvs);
        }
    }

    @Override
    public String toString() {
        return "TVRepository{" +
                "tvs=" + Arrays.toString(tvs) +
                '}';
    }
}
