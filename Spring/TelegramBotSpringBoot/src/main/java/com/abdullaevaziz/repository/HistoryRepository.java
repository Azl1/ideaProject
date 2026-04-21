package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.History;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByTelegramUserId(long telegramUserId);
    Optional<History> findByTelegramUserIdAndTelegramMessageId(long telegramUserId, long telegramMessageId);
}
