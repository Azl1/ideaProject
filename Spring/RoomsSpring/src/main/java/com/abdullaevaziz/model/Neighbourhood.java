package com.abdullaevaziz.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "neighbourhood",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "borough"})})
public class Neighbourhood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String borough;

    public String toCSV() {
        return this.name + ";" + this.borough;
    }

}
