package com.abdullaevaziz.model;

import java.util.Objects;

/**
 * Заочники
 */
public class DistanceLearning extends Student {

    private String address;

    public DistanceLearning(String name, String family, int course) {
        super(name, family, course);
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
        DistanceLearning that = (DistanceLearning) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "DistanceLearning{" +
                "address='" + address + '\'' +
                '}';
    }

    public String toCSV(){
        return super.toCSV() + ";" + this.address;
    }



}
