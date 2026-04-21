package com.kirillkotov.service;

import com.kirillkotov.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> get();

    User get(String id);

    List<User> getAllByFirstName(String firstName);

    User getByLogin(String login);

    User delete(String id);

    User update(User user);

    User addTvs(User user);
}
