package com.abdullaevaziz.repository;



import com.abdullaevaziz.model.TelegramUser;
import com.pengrad.telegrambot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {


}
