package org.abdullaevaziz.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_long")
public class NotificationLong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_id", nullable = false)
    private long orderId;

    private String email;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime sentAt;

}
