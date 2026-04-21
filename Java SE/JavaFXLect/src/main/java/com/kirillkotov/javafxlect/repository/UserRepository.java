package com.kirillkotov.javafxlect.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kirillkotov.javafxlect.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.kirillkotov.javafxlect.util.Constants;

/**
 * Write com.fasterxml.jackson.databind.ObjectMapper manually, next alt + insert -> add requires ...
 * Next add exports com.kirillkotov.javafxlect.model to com.fasterxml.jackson.databind; to module-info.java
 */
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The same for JavaTimeModule
 */
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public UserRepository() {
        try {
            this.users = this.objectMapper.readValue(new File(Constants.USERS_DB), new TypeReference<>() {
            });
        } catch (IOException ignored) {
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void add(User user) {
        user.setId(this.users.stream().mapToLong(User::getId).max().orElse(0) + 1);
        this.users.add(user);
        this.save();
    }

    public void delete(User user) {
        this.users.remove(user);
        this.save();
    }

    public void save() {
        try {
            this.objectMapper.writeValue(new File(Constants.USERS_DB), this.users);
        } catch (IOException ignored) {}
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                '}';
    }
}
