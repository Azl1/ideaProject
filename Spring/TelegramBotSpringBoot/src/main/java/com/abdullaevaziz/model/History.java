package com.abdullaevaziz.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "History")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private TelegramUser telegramUser;

    @NonNull
    private String action;

    @CreatedDate
    private LocalDateTime timestamp;

    @NonNull
    private long telegramMessageId;
}
