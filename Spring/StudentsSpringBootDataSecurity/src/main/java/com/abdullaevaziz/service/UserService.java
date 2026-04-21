package com.abdullaevaziz.service;

import com.abdullaevaziz.model.User;

public interface UserService {
    User get(long id);
    User add(User user);
}
