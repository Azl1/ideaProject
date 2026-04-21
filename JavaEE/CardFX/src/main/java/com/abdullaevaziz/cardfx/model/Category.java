package com.abdullaevaziz.cardfx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@ToString(onlyExplicitlyIncluded = true)
public class Category {

    @ToString.Exclude
    private long id;

    @NonNull
    @ToString.Include
    private String name;


    private User user;

    @JsonIgnore
    @ToString.Exclude
    private List<Card> cardsList = new ArrayList<>();


}
