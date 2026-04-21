package com.abdullaevaziz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="cards", uniqueConstraints = {@UniqueConstraint(columnNames = {"question", "answer", "categoryId"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @JsonFormat //TODO норм
    @Column(nullable = false)
    private Date creationDate = new Date();

}
