package com.kirillkotov.springsecurityjpa.service;

import com.kirillkotov.springsecurityjpa.model.Role;
import com.kirillkotov.springsecurityjpa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }


    @Override
    public void add(Role role) {
        try {
            this.roleRepository.save(role);
        } catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Role has already added!");
        }
    }

    @Override
    public Role get(long id) {
        return this.roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role does not exists!"));
    }
}
