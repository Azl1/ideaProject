package com.kirillkotov.repository;

import com.kirillkotov.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByFirstName(String firstName);

    Optional<User> findUserByLogin(String login);
}
