package com.kirillkotov.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tvs",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"brand", "model"})})
public class TV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String brand;

    @NonNull
    private String model;

    @NonNull
    private String color;

    @NonNull
    private int timeExpectancy;

    @NonNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private double price;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate produceDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
