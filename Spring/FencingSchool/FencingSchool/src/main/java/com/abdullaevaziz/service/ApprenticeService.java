package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Apprentice;

import java.util.List;

public interface ApprenticeService {

    void add(Apprentice apprentice);

    List<Apprentice> get();

    Apprentice get(long id);

    Apprentice delete(long id);

    Apprentice update(Apprentice apprentice);
}
