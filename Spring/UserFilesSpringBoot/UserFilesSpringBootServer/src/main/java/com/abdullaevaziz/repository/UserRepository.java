package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.User;
import com.abdullaevaziz.model.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);


}
