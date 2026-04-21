package com.kirillkotov.service;

import com.kirillkotov.model.User;

import java.util.List;

public interface UserService {
    User add(User user);
    List<User> get();
    User get(long id);
    List<User> get(String name);
}
