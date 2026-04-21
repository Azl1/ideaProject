package com.abdullaevaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Apprentice" , uniqueConstraints = {@UniqueConstraint(columnNames = {"phoneNumber"})})
/**
 * 2. Apprentice – ученик школы фехтования
 */
public class Apprentice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    private String patronymic;

    @Column(nullable = false)
    private String phoneNumber;

}
