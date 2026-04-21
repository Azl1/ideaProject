package com.kirillkotov.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirillkotov.model.Vehicle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Vehicle> data = new ArrayList<>();

    public VehicleRepository() {
    }

    public VehicleRepository(String file) throws IOException {
        this.data = this.objectMapper.readValue(new File(file), new TypeReference<>() {});
    }

    public List<Vehicle> getData() {
        return data;
    }

    public void add(Vehicle vehicle){
        this.data.add(vehicle);
    }

    public void save(String fileName) throws IOException {
        this.objectMapper.writerFor(new TypeReference<List<Vehicle>>() {})
                .writeValue(new File(fileName),this.data);
    }

    @Override
    public String toString() {
        return "Fleet{" +
                "vehicles=" + data +
                '}';
    }
}
