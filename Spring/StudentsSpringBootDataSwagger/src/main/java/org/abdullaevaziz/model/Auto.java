package org.abdullaevaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "autos" , uniqueConstraints = {@UniqueConstraint(columnNames = {"brand", "power", "year","student_id"})})
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Brand enter")
    private String brand;

    @Positive(message = "power must be positive")
    private int power;

    @Positive(message = "Year must be positive")
    private int year;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Valid
    //@NotNull(message = "Student couldn't be null")
    private Student student;
}
