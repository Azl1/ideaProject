package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Admin;

public interface AdminService {
    Admin get(long id);
    Admin add(Admin admin);
}
