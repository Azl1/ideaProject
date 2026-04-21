package com.abdullaevaziz.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Очники
 */
public class FullTime extends Student{

    private ArrayList<Integer> ratingsList = new ArrayList<>();

    public FullTime(String name, String family, int courses) {
        super(name, family, courses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FullTime fullTime = (FullTime) o;
        return Objects.equals(ratingsList, fullTime.ratingsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ratingsList);
    }

    @Override
    public String toString() {
        return "FullTime{" +
                "ratingsList=" + ratingsList +
                '}';
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ";" + this.ratingsList;
    }

    public void add(int rating){
        ratingsList.add(rating);
    }

    public boolean isExcellent(){
        for (Integer value : this.ratingsList) {
            if (value != 5) {
                return false;
            }
        }
        return true;
    }

}
