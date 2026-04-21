package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Trainer;
import com.abdullaevaziz.model.User;

import java.util.List;

public interface TrainerService {

    void add(Trainer trainer);

    List<Trainer> get();

    Trainer get(long id);

    Trainer delete(long id);

    Trainer update(Trainer trainer);
}
