package com.abdullaevaziz.util;

import java.util.*;

public class Station implements Comparable<Station> {
    /**
     * Указание:
     * Разработать схему данных и коллекций(структур данных),
     * необходимых для рационального решения поставленных задач и
     * согласовать ее со своим руководителем проекта
     */
    private String name;
    private int passengerFlow;

    private Line line;
    public Station() {
    }

    public Station(String name, int passengerFlow, Line line) {
        this.name = name;
        this.passengerFlow = passengerFlow;
        this.line = line;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassengerFlow() {
        return passengerFlow;
    }

    public void setPassengerFlow(int passengerFlow) {
        this.passengerFlow = passengerFlow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return passengerFlow == station.passengerFlow && Objects.equals(name, station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passengerFlow);
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", passengerFlow=" + passengerFlow +
                '}';
    }

    /**
     * CSV
     */
    public String toCSV() {
        return this.line.toCSV() + ";" + this.name + ";" + this.passengerFlow;
    }

    @Override
    public int compareTo(Station o1) {
        return this.name.compareTo(o1.getName());
    }

}
