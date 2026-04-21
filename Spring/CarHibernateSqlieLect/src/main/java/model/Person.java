package model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    // Связь 1 к 1 с аккаунтом
    @ToString.Exclude
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Account account;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @Column(nullable = false)
    @NonNull
    private int age;

    @Column(nullable = false)
    @NonNull
    private double salary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    @ToString.Exclude
    private List<Car> cars = new ArrayList<>();

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "persons_hotels", joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id"))

    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private List<Hotel> hotels = new ArrayList<>();

    // Enum с должностями, отображающийся как строка
    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    public void  addHotel(Hotel hotel){
        this.hotels.add(hotel);
    }

}
