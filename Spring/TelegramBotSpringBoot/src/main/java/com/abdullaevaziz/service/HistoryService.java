package com.abdullaevaziz.service;

import com.abdullaevaziz.model.History;

import java.util.List;
import java.util.Optional;

public interface HistoryService {

    //History get(long id);
    History saveHistory(History history);
    List<History> findAllByTelegramUserIdList(long telegramUserId);
    History findByTelegramUserIdAndTelegramMessageId(long telegramUserId, long telegramMessageId);
}
