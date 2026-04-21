package com.kirillkotov.springsecurityjpa.repository;

import com.kirillkotov.springsecurityjpa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
