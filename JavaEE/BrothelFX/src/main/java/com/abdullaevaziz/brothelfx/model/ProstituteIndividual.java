package com.abdullaevaziz.brothelfx.model;

import java.util.Objects;

public class ProstituteIndividual {

    private int id;
    private String fio;
    private int age;
    private int weight;
    private String specialization;
    private double pricePerHour;
    private int id_cl;

    public ProstituteIndividual() {
    }

    public ProstituteIndividual(String fio, int age, int weight, String specialization, double pricePerHour, int id_cl) {
        this.fio = fio;
        this.age = age;
        this.weight = weight;
        this.specialization = specialization;
        this.pricePerHour = pricePerHour;
        this.id_cl = id_cl;
    }

    public ProstituteIndividual(int id, String fio, int age, int weight, String specialization, double pricePerHour, int id_cl) {
        this.id = id;
        this.fio = fio;
        this.age = age;
        this.weight = weight;
        this.specialization = specialization;
        this.pricePerHour = pricePerHour;
        this.id_cl = id_cl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public int getId_cl() {
        return id_cl;
    }

    public void setId_cl(int id_cl) {
        this.id_cl = id_cl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProstituteIndividual that = (ProstituteIndividual) o;
        return id == that.id && age == that.age && weight == that.weight && Double.compare(that.pricePerHour, pricePerHour) == 0 && id_cl == that.id_cl && Objects.equals(fio, that.fio) && Objects.equals(specialization, that.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, age, weight, specialization, pricePerHour, id_cl);
    }

    @Override
    public String toString() {
        return "ProstituteIndividual{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", specialization='" + specialization + '\'' +
                ", pricePerHour=" + pricePerHour +
                ", id_cl=" + id_cl +
                '}';
    }
}
