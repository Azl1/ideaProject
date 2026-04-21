package com.abdullaevaziz.repository;

import com.abdullaevaziz.exceptions.ConstraintViolationException;
import com.abdullaevaziz.model.Auto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AutoRepository {
    @Value("${datasource.filename2}")
    private String fileName;

    private HashMap<Long, Auto> autoHashMap = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        try {
            ArrayList<Auto> autoArrayList = this.objectMapper.readValue(new File(fileName),
                    new TypeReference<>() {
                    });
            this.autoHashMap = (HashMap<Long, Auto>) autoArrayList.stream().collect(Collectors.toMap(Auto::getId, x -> x));
        } catch (IOException ignored) {
        }
    }


    private void save() {
        try {
            this.objectMapper.writeValue(new File(this.fileName), this.autoHashMap.values());
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    public void save(Auto auto) throws ConstraintViolationException {
        if (this.autoHashMap.values().stream().anyMatch
                (x -> x.getBrand().equals(auto.getBrand())
                        && x.getYear() != auto.getYear() && x.getId() != auto.getId())) {
            throw new ConstraintViolationException("Duplicate entry");
        }
        if (auto.getId() == 0) {
            long id = autoHashMap.keySet().stream().mapToLong(x -> x).max().orElse(0L) + 1;
            auto.setId(id);
        }
        this.autoHashMap.put(auto.getId(), auto);
        this.save();
    }

    public void update(Auto auto) throws ConstraintViolationException {
        auto.setBrand(auto.getBrand());
        auto.setPower(auto.getPower());
        auto.setYear(auto.getYear());
        save(auto);
    }

    public List<Auto> findAll() {
        return new ArrayList<>(this.autoHashMap.values());
    }

    public Optional<Auto> findById(long id) {
        return Optional.ofNullable(this.autoHashMap.get(id));
    }

    public List<Auto> findByBrand(String brand) {
        return this.autoHashMap.values().stream()
                .filter(x -> x.getBrand().equals(brand)).collect(Collectors.toList());
    }

    public List<Auto> findByStudentId(long idStudent) {
        return this.autoHashMap.values().stream()
                .filter(auto -> auto.getStudent().getId() == idStudent)
                .collect(Collectors.toList());
    }

    public void delete(long id) {
        this.autoHashMap.remove(id);
        this.save();
    }
}
