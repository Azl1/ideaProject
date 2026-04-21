package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Admin;
import com.abdullaevaziz.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    private AdminRepository adminRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setAdminRepository(AdminRepository userRepository) {
        this.adminRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin get(long id) {
        return this.adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Admin not found"));
    }

    @Override
    public Admin add(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        try {
            return this.adminRepository.save(admin);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Admin has already exist");
        }
    }
}
