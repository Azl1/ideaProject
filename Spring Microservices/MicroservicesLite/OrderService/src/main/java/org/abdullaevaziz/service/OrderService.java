package org.abdullaevaziz.service;

import org.abdullaevaziz.model.NotificationLong;
import org.abdullaevaziz.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {

    void add(Order order, String email);
    List<Order> getList();
}
