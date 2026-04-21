package org.abdullaevaziz.service;

import feign.FeignException;
import lombok.AllArgsConstructor;
import org.abdullaevaziz.client.NotificationClient;
import org.abdullaevaziz.model.NotificationLong;
import org.abdullaevaziz.model.Order;
import org.abdullaevaziz.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private NotificationClient notificationClient;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setNotificationClient(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    @Transactional
    @Override
    public void add(Order order, String email) {
        try {
            this.orderRepository.save(order);
            try {
                notificationClient.sendNotification(new NotificationLong(order.getId(), email));
            } catch (FeignException e) {
                throw new IllegalArgumentException("NotificationLong service doesn't work correct");
            }
        } catch (
                DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Order is already exists");
        }
    }

    @Transactional
    @Override
    public List<Order> getList() {
         return this.orderRepository.findAll();

    }
}
