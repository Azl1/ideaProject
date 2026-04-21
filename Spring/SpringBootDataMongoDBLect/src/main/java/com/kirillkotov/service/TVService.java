package com.kirillkotov.service;

import com.kirillkotov.model.TV;

import java.util.List;

public interface TVService {

    void add(String userId, TV tv);

    List<TV> get();

    TV get(String id);

    TV delete(String id);

    TV update(TV tv);

    void updatePrice();
}
