package com.kirillkotov.model;

import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="parts", uniqueConstraints
        = {@UniqueConstraint(columnNames = {"name", "count"})})
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NonNull
    @Column(name="name")
    private String name;

    @NonNull
    @Column(name="need")
    private boolean need;

    @NonNull
    @Column(name="count")
    private int count;
}
