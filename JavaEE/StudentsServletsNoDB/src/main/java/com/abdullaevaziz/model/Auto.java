package com.abdullaevaziz.model;

import java.util.Objects;

public class Auto {

    private int id;
    private String brand;
    private double power;
    private int year;
    private int idStudent;

    public Auto() {
    }

    public Auto(String brand, double power, int year, int idStudent) {
        this.brand = brand;
        this.power = power;
        this.year = year;
        this.idStudent = idStudent;
    }

    public Auto(int id, String brand, double power, int year, int idStudent) {
        this.id = id;
        this.brand = brand;
        this.power = power;
        this.year = year;
        this.idStudent = idStudent;
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

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto that = (Auto) o;
        return id == that.id && Double.compare(that.power, power) == 0 && year == that.year && idStudent == that.idStudent && Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, power, year, idStudent);
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", power=" + power +
                ", year=" + year +
                ", idStudent=" + idStudent +
                '}';
    }
}
