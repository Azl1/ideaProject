package model;

import lombok.*;
import org.hibernate.annotations.Cascade;

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
    private int id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String brand;

    @NonNull
    @Column(nullable = false)
    private  double rate;

    @NonNull
    @Column(nullable = false)
    private int service;

    @ManyToMany
    @ToString.Exclude
    @JoinTable (name = "persons_hotels",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private List<Person> persons = new ArrayList<>();

    public void  addPerson (Person person){
        this.persons.add(person);
    }
}
