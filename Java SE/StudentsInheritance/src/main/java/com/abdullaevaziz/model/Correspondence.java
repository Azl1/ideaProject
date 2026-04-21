package com.abdullaevaziz.model;

import java.util.Objects;

/**
 * Заочники
 */
public class Correspondence extends Student{

    private String address;

    public Correspondence(String name, String family, int courses, String address) {
        super(name, family, courses);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Correspondence that = (Correspondence) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address);
    }

    @Override
    public String toString() {
        return "Correspondence{" +
                "address='" + address + '\'' +
                '}';
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ";" + this.address;
    }
}
