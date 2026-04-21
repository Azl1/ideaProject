package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Admin;
import com.abdullaevaziz.model.Apprentice;
import com.abdullaevaziz.model.Trainer;
import com.abdullaevaziz.model.User;
import org.springframework.security.core.Authentication;

public interface UserService {
    User getAuthenticatedUser(Authentication authentication);
    User get(long id);
    User delete (long id);

}
