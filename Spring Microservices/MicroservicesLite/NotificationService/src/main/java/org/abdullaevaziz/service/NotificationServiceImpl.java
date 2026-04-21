package org.abdullaevaziz.service;


import org.abdullaevaziz.model.NotificationLong;
import org.abdullaevaziz.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;

    @Autowired
    public void setNotificationRepository(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }
    @Override
    public void add(NotificationLong notificationLong) {
        if (notificationLong.getSentAt() == null) {
            notificationLong.setSentAt(LocalDateTime.now());
        }
        this.notificationRepository.save(notificationLong);
    }

    public List<NotificationLong> getList() {
        return this.notificationRepository.findAll();
    }
}
