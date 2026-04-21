package com.abdullaevaziz.service;

import com.abdullaevaziz.model.History;
import com.abdullaevaziz.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private HistoryRepository historyRepository;

    @Autowired
    public void setHistoryRepository(HistoryRepository historyRepository){
        this.historyRepository = historyRepository;
    }

    @Override
    public History saveHistory(History history) {
        try {

            history.setTimestamp(LocalDateTime.now());
            return this.historyRepository.save(history);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("History has already added!");
        }
    }


    /*@Override
    public History get(long id) {
        return historyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("History does not exists!"));
    }*/

    @Override
    public List<History> findAllByTelegramUserIdList (long telegramUserId) {
        return this.historyRepository.findAllByTelegramUserId(telegramUserId);
    }

    @Override
    public History findByTelegramUserIdAndTelegramMessageId(long telegramUserId, long telegramMessageId) {
        return this.historyRepository.findByTelegramUserIdAndTelegramMessageId(telegramUserId, telegramMessageId)
                .orElseThrow(() -> new IllegalArgumentException("History not found!"));
    }


}
