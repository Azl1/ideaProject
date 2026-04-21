package com.abdullaevaziz.repository;


import com.abdullaevaziz.model.ComplimentsTelegramUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplimentsTelegramUsersRepository extends JpaRepository<ComplimentsTelegramUsers, Long> {

    List<ComplimentsTelegramUsers> findAllByTelegramUserId(long userId);

    void deleteAllByTelegramUserId(long userId);
}
