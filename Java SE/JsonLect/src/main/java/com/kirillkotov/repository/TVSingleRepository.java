package com.kirillkotov.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kirillkotov.model.TV;

import java.io.File;
import java.io.IOException;

/**
 * @author Kotov Kirill
 * Add dependencies from following links for working with class
 * https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
 * https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310
 */
public class TVSingleRepository {
    private TV tv;

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        //TODO для работы с LocalDateTime
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public TVSingleRepository() {
    }

    public TVSingleRepository(TV tv) {
        this.tv = tv;
    }

    /**
     * Read TV using File
     *
     * @param fileName Path of json file
     * @throws IOException
     */
    public TVSingleRepository(String fileName) throws IOException {
        this.tv = objectMapper.readValue(new File(fileName), TV.class);
    }

    /**
     * Save TV using File
     *
     * @param fileName Path of json file
     * @throws IOException
     */
    public void save(String fileName) throws IOException {
        objectMapper.writeValue(new File(fileName), tv);
    }

    public void setTv(TV tv){
        this.tv = tv;
    }

    public TV getTv() {
        return tv;
    }

    @Override
    public String toString() {
        return "TVSingleRepository{" +
                "tv=" + tv +
                '}';
    }
}
