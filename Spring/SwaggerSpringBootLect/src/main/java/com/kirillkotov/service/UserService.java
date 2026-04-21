package com.kirillkotov.service;

import com.kirillkotov.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> get();
    User get(long id);
    User get(String username);
    User delete(long id);
    User update(User user);
}
