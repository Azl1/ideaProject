package com.kirillkotov.service;

import com.kirillkotov.model.Mammal;

import java.util.List;

public interface MammalService {

    List<Mammal> get();

    Mammal get(long id);

    Mammal add(Mammal mammal);

    Mammal delete(long id);

    Mammal update(Mammal mammal);
}
