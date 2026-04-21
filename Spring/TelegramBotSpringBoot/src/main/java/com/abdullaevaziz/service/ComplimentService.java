package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Compliment;

import java.util.List;
import java.util.Optional;

public interface ComplimentService {

    Compliment add(Compliment compliment);
    List<Compliment> getList();
    Compliment getById(long id);
    Compliment getComplimentRandom(long id);

}
