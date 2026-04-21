package com.abdullaevaziz.chatspringbootfx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Message {

    private long id;

    @NonNull
    private String text;


    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp = LocalDateTime.now();

    private User user;


    @Override
    public String toString() {
        return "Message = " + text + '\'' +  user +
                " (timestamp=" + timestamp + ")";
    }
}
