package com.abdullaevaziz.cardfxspring.model;

import lombok.*;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Card {

    @ToString.Exclude
    private long id;
    @NonNull
    private String question;
    @NonNull
    private String answer;

    @ToString.Exclude
    private Category category;

    @ToString.Exclude
    private Date creationDate = new Date();

}
