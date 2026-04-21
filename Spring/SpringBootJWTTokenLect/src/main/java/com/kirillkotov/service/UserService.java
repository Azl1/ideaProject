package com.kirillkotov.service;


import com.kirillkotov.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    User addRole(long userId, long roleId);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    User delete(Long id);
}
