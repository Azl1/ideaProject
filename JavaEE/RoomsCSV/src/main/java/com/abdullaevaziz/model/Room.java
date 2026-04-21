package com.abdullaevaziz.model;

import java.util.Objects;

public class Room {
    private long id;
    private String name;
    private double price;
    private int minimumNights;
    private int numberOfReviews;
    private String roomType;
    private Host host;
    private Neighbourhood neighbourhood;
    private int daysOpen;


    public Room() {
    }

    public Room(long id, String name, double price, int minimumNights, int numberOfReviews, String roomType, Host host, Neighbourhood neighbourhood, int daysOpen) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.minimumNights = minimumNights;
        this.numberOfReviews = numberOfReviews;
        this.roomType = roomType;
        this.host = host;
        this.neighbourhood = neighbourhood;
        this.daysOpen = daysOpen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMinimumNights() {
        return minimumNights;
    }

    public void setMinimumNights(int minimumNights) {
        this.minimumNights = minimumNights;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Neighbourhood getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(Neighbourhood neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public int getDaysOpen() {
        return daysOpen;
    }

    public void setDaysOpen(int daysOpen) {
        this.daysOpen = daysOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && Double.compare(room.price, price) == 0 && minimumNights == room.minimumNights && numberOfReviews == room.numberOfReviews && daysOpen == room.daysOpen && Objects.equals(name, room.name) && Objects.equals(roomType, room.roomType) && Objects.equals(host, room.host) && Objects.equals(neighbourhood, room.neighbourhood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, minimumNights, numberOfReviews, roomType, host, neighbourhood, daysOpen);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", minimumNights=" + minimumNights +
                ", numberOfReviews=" + numberOfReviews +
                ", roomType='" + roomType + '\'' +
                ", host=" + host +
                ", neighbourhood=" + neighbourhood +
                ", daysOpen=" + daysOpen +
                '}';
    }
}
