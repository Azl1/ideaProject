package com.abdullaevaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Entity
@Table(name = "compliments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compliment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    @Override
    public String toString() {
        return "id =" + id +
                ", text ='" + text + '\'';
    }
}
