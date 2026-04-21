package com.abdullaevaziz.quizspringbootfx.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class History {

    private long id;

    @NonNull
    private Result result;

    @NonNull
    private String answer;

    @NonNull
    private boolean correct;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp = LocalDateTime.now();

    @JsonIgnoreProperties(ignoreUnknown = true)
    private User user;

}
