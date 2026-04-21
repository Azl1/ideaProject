package org.abdullaevaziz.repository;

import org.abdullaevaziz.model.NotificationLong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationLong, Long> {
}
