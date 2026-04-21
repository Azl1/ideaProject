package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findAllByLoginAndPassword(String login, String password);
}
