package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Admin;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AdminService {

    Admin add(Admin admin);

    List<Admin> getListAdmin();

    Admin get(Authentication authentication, long id) throws IllegalAccessException;

    Admin update(Authentication authentication, Admin admin) throws IllegalAccessException;

    Admin delete(Authentication authentication, long id) throws IllegalAccessException;
}
