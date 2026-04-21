package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   /* List<User> findByUser(User user);

    List<User> findAllByUserId(long userId);
    Optional<User> findByUserIdAndFilename(long userId, String filename);*/

    Optional<User> findByLogin(String login);
}
