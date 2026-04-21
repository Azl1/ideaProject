package com.kirillkotov.service;

import com.kirillkotov.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User get(Long id);

    User addUser(User user);

    User delete(Long id);

    List<User> delete(String name);

    User updateUser(User user);
}
