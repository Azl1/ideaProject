package model;

import java.util.Objects;

public class Auto {

    private int id;
    private String brand;
    private int power;
    private int year;
    private int id_s;

    public Auto() {
    }

    public Auto(int id, String brand, int power, int year, int id_s) {
        this.id = id;
        this.brand = brand;
        this.power = power;
        this.year = year;
        this.id_s = id_s;
    }

    public Auto(String brand, int power, int year, int id_s) {
        this.brand = brand;
        this.power = power;
        this.year = year;
        this.id_s = id_s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId_s() {
        return id_s;
    }

    public void setId_s(int id_s) {
        this.id_s = id_s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return id == auto.id && power == auto.power && year == auto.year && id_s == auto.id_s && Objects.equals(brand, auto.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, power, year, id_s);
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", power=" + power +
                ", year=" + year +
                ", id_s=" + id_s +
                '}';
    }
}
