package com.abdullaevaziz.service;

import com.abdullaevaziz.model.*;
import com.abdullaevaziz.repository.AdminRepository;
import com.abdullaevaziz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private UserRepository userRepository;
    private AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Admin add(Admin admin) {
        try {
           admin.setPassword(passwordEncoder.encode(admin.getPassword()));
           return this.adminRepository.save(admin);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Admin has already added!");
        }
    }

    /*@Override
    public Admin get(long id) {
        return this.adminRepository.
                findById(id).orElseThrow(() -> new IllegalArgumentException("Admin does not exists!"));
    }*/
    @Override
    public Admin get(Authentication authentication, long id) throws IllegalAccessException {
        long userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        User user = this.userRepository.findById(userId).
                orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        if (user instanceof Admin) {
            return this.adminRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        }
        return this.adminRepository.
                findById(id).orElseThrow(() -> new IllegalArgumentException("Admin does not exists!"));
    }

    @Override
    public List<Admin> getListAdmin() {
        return this.adminRepository.findAll();
    }

    @Override
    public Admin update(Authentication authentication, Admin admin)  throws IllegalAccessException {
        try {
            Admin old = this.get(authentication, admin.getId());
            old.setLogin(admin.getLogin());
            old.setSurname(admin.getSurname());
            old.setName(admin.getName());
            old.setPatronymic(admin.getPatronymic());

            String password = admin.getPassword();
            if (!password.equals("***")){
                old.setPassword(passwordEncoder.encode(admin.getPassword()));
            }

            old.setEmail(admin.getEmail());
            old.setSalary(admin.getSalary());
            this.adminRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Admin has already added!");
        }
    }


    @Override
    public Admin delete(Authentication authentication, long id) throws IllegalAccessException {
        Admin adminDelete = this.get(authentication,id);
        this.adminRepository.delete(adminDelete);
        return adminDelete;
    }
}
