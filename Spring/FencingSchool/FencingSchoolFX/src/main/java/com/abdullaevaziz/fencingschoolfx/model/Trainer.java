package com.abdullaevaziz.fencingschoolfx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
/**
 * 3. Trainer – тренер школы фехтования
 */

public class Trainer {


    private long id;

    @NonNull
    private String surname;

    @NonNull
    private String name;

    @NonNull
    private String patronymic;

    @NonNull
    private int experience;

    @JsonIgnore
    @ToString.Exclude
    private TrainerSchedule trainerSchedule;
}
