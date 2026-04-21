package com.kirillkotov.springsecurityjpa.service;

import com.kirillkotov.springsecurityjpa.model.User;

public interface UserService {
    User get(long id);
    User add(User user);
}
