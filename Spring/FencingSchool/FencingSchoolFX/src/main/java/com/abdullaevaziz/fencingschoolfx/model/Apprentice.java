package com.abdullaevaziz.fencingschoolfx.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
/**
 * 2. Apprentice – ученик школы фехтования
 */
public class Apprentice {


    private long id;

    @NonNull
    private String surname;

    @NonNull
    private String name;

    @NonNull
    private String patronymic;

    @NonNull
    private long phoneNumber;

}
