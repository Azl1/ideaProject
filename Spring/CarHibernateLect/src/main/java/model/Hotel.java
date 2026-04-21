package model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String brand;

    @NonNull
    @Column(nullable = false)
    private double rate;

    @NonNull
    @Column(nullable = false)
    private int service;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "persons_hotels",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> people = new ArrayList<>();

    public void addPerson(Person person){
        this.people.add(person);
    }
}
