package com.abdullaevaziz.service;

import com.abdullaevaziz.model.User;

public interface UserService {

    User add (User user);
    User findByUsername(String login);

    User get(long id);
}
