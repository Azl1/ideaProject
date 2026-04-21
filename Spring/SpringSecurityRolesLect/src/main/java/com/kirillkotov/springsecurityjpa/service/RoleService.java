package com.kirillkotov.springsecurityjpa.service;

import com.kirillkotov.springsecurityjpa.model.Role;
import com.kirillkotov.springsecurityjpa.model.User;

import java.util.List;

public interface RoleService {

    void add(Role role);

    public Role get(long id);
}
