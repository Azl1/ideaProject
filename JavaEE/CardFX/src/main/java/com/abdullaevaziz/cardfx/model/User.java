package com.abdullaevaziz.cardfx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private long id;

    @NonNull
    @ToString.Exclude
    private String login;

    @NonNull
    @ToString.Exclude
    private String password;

    @NonNull
    @ToString.Exclude
    private String name;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date regDate = new Date();

    @JsonIgnore
    @ToString.Exclude
    private List<Category> categoryList = new ArrayList<>();


}
