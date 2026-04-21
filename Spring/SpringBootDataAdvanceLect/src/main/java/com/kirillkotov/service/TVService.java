package com.kirillkotov.service;

import com.kirillkotov.model.TV;

import java.util.List;


public interface TVService {
    void add(long userId, TV tv);

    List<TV> get();

    TV get(long id);

    TV update(TV tv);

    TV delete(long id);

    TV get(String brand, String model);

    List<TV> getByUserFirstName(String firstName);

    List<TV> deleteByBrandAndColor(String brand, String color);

    void delete(String brand, String model);

    List<TV> get(List<String> brands);

    int updateColor(String brand, String color);
}
