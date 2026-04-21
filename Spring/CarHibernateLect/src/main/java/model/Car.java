package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "cars",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"brand", "model"})})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NonNull
    private String brand;

    @Column(nullable = false)
    @NonNull
    private String model;

    @Column(nullable = false)
    @NonNull
    private String color;

    @Column(nullable = false, name = "`range`")
    @NonNull
    private double range;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}
