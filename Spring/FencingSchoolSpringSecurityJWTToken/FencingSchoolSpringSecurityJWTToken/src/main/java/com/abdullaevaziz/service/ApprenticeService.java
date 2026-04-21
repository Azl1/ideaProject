package com.abdullaevaziz.service;

import org.springframework.security.core.Authentication;
import com.abdullaevaziz.model.Apprentice;

import java.util.List;

public interface ApprenticeService {

    Apprentice add(Apprentice apprentice);

    List<Apprentice> getListApprentice();

    Apprentice get(Authentication authentication, long id) throws IllegalAccessException;

    Apprentice update(Authentication authentication, Apprentice apprentice) throws IllegalAccessException;

    Apprentice delete(Authentication authentication, long id) throws IllegalAccessException;
}
