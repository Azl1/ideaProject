package com.kirillkotov.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirillkotov.exceptions.ConstraintViolationException;
import com.kirillkotov.model.TV;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TVRepository {
    @Value("${datasource.filename}")
    private String filename;


    private Map<Long, TV> tvHashMap = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        try {
            ArrayList<TV> list = this.objectMapper.readValue(new File(filename),
                    new TypeReference<>() {
            });
            this.tvHashMap = list.stream().collect(Collectors.toMap(TV::getId, x -> x));
        } catch (IOException ignored) {
        }try {
            ArrayList<TV> list = this.objectMapper.readValue(new File(filename),
                    new TypeReference<>() {
                    });
            this.tvHashMap = list.stream().collect(Collectors.toMap(TV::getId, x -> x));
        } catch (IOException ignored) {
        }
    }

    public void save(TV tv) throws ConstraintViolationException {
        if (this.tvHashMap.values().stream().anyMatch(x -> x.getBrand()
                .equals(tv.getBrand()) && x.getModel().equals(tv.getModel()) && x.getId() != tv.getId())) {
            throw new ConstraintViolationException("Duplicate entry");
        }
        if(tv.getId() == 0) {
            long id = tvHashMap.keySet().stream().mapToLong(x -> x).max().orElse(0L) + 1;
            tv.setId(id);
        }
        this.tvHashMap.put(tv.getId(), tv);
        this.save();
    }

    private void save() {
        try {
            this.objectMapper.writeValue(new File(this.filename), this.tvHashMap.values());
        } catch (IOException ignored) {
        }
    }

    public List<TV> findAll() {
        return new ArrayList<>(this.tvHashMap.values());
    }

    public Optional<TV> findById(long id) {
        return Optional.ofNullable(this.tvHashMap.get(id));
    }

    public List<TV> findByBrand(String brand) {
        return this.tvHashMap.values().stream()
                .filter(x -> x.getBrand().equals(brand)).collect(Collectors.toList());
    }

    public void deleteById(long id) {
        this.tvHashMap.remove(id);
        this.save();
    }
}
