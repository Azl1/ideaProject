package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Trainer;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TrainerService {

    Trainer add (Trainer trainer);

    List<Trainer> getListTrainer();

    Trainer get(Authentication authentication, long id) throws IllegalAccessException;

    Trainer update(Authentication authentication, Trainer trainer) throws IllegalAccessException;

    Trainer delete(Authentication authentication, long id) throws IllegalAccessException;

}
