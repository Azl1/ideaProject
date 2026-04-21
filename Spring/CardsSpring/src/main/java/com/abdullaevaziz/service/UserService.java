package com.abdullaevaziz.service;

import com.abdullaevaziz.model.User;

import java.util.List;

public interface UserService {

    void add(User user);
    List<User> get();
    User get(long id);
    User get(String login, String password);
    User update(User user);
    User delete(long id);


}
