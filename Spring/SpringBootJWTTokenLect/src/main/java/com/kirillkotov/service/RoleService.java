package com.kirillkotov.service;

import com.kirillkotov.model.Role;

public interface RoleService  {
    void add(Role role);

    Role get(long id);
}
