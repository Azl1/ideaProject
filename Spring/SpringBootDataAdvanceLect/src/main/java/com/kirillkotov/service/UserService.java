package com.kirillkotov.service;

import com.kirillkotov.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> get();
    User get(long id);
    User delete(long id);
    User update(User user);
    List<User> get(String firstName);
    List<User> get(String firstName, String lastName);
    List<User> getAllByOrOrderByFirstNameAsc();
    List<User> getAllByOrOrderByFirstNamDesc();

}
