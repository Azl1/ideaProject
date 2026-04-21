package com.abdullaevaziz.service;

import com.abdullaevaziz.model.User;
import com.abdullaevaziz.model.UserType;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    void addUser(User user, UserType userType);
    void addAdmin(User user, UserType userType);
    User findByUsername(String login);
    User get(long id);
    List<User> getListUsers(Authentication authentication);

}
