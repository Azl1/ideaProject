package com.kirillkotov.service;

import com.kirillkotov.model.Role;
import com.kirillkotov.repository.RoleRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void add(Role role){
        try {
            this.roleRepository.save(role);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Role is already exists!");
        }
    }
    @Override
    public Role get(long id) {
        return this.roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role doesn't exist"));
    }

}
