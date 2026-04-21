package com.abdullaevaziz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Training {

    private long id;

    private int numberGym;

    @ToString.Exclude
    private Trainer trainer;

    @ToString.Exclude
    private  Apprentice apprentice;

    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime timeStart;

    public  Training(int numberGym, LocalDate date, LocalTime timeStart){
        this.numberGym = numberGym;
        this.date = date;
        this.timeStart = timeStart;
    }
}
