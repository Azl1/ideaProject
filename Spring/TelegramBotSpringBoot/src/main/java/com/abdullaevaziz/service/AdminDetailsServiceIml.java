package com.abdullaevaziz.service;


import com.abdullaevaziz.model.Admin;
import com.abdullaevaziz.model.AdminDetailsImpl;
import com.abdullaevaziz.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminDetailsServiceIml implements UserDetailsService {

    private AdminRepository adminRepository;

    @Autowired
    public void setUserRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Admin> user = adminRepository.findByUserName(login);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + login));

        return user.map(AdminDetailsImpl::new).get();
    }
}
