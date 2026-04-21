package com.kirillkotov.repository;

import com.kirillkotov.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByFirstName(String firstName);
    List<User> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<User> findAllByOrderByFirstNameAsc();
    List<User> findAllByOrderByFirstNameDesc();

}
