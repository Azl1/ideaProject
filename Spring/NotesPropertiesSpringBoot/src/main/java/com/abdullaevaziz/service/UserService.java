package com.abdullaevaziz.service;

import com.abdullaevaziz.model.User;
import org.springframework.security.core.Authentication;

public interface UserService {
    User getAuthenticatedUser(Authentication authentication);
    User get(long id);

    public User add(User user);
}
