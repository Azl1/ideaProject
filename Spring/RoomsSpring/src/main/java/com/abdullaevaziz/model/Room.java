package com.abdullaevaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "host_id", "neighbourhood_id"})})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private double price;

    private int minimumNights;

    private int numberOfReviews;

    private String roomType;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;

    @ManyToOne
    @JoinColumn(name = "neighbourhood_id", nullable = false)
    private Neighbourhood neighbourhood;

    private int daysOpen;

    public String toCSV() {
        String formatPrice = String.format(" $%.2f ", this.price)
                .replace(".", ",");
        return this.id + ";" + this.name
                + ";" + this.host.getId() + ";" + this.host.getName()
                + ";" + this.neighbourhood.getName() + ";" + this.roomType
                + ";" + formatPrice + ";" + this.minimumNights
                + ";" + this.numberOfReviews + ";" + this.daysOpen;
    }
}
