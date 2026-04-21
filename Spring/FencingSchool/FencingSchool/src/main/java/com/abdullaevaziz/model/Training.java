package com.abdullaevaziz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Training", uniqueConstraints = {@UniqueConstraint(columnNames = {"apprenticeId", "date"})})
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int numberGym;

    @ManyToOne
    @JoinColumn(name = "trainerId", nullable = false)
    @ToString.Exclude
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "apprenticeId", nullable = false)
    @ToString.Exclude
    private Apprentice apprentice;

    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime timeStart;

}
