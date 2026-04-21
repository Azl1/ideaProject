package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Compliment;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ComplimentService {

    List<Compliment> getList();

    Compliment getById(Authentication authentication, long id);
    Compliment getComplimentRandom(Authentication authentication);
}
