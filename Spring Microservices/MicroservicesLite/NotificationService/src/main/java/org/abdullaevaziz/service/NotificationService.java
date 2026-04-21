package org.abdullaevaziz.service;


import org.abdullaevaziz.model.NotificationLong;

import java.util.List;

public interface NotificationService {

    void add(NotificationLong notificationLong);

    List<NotificationLong> getList();
}
