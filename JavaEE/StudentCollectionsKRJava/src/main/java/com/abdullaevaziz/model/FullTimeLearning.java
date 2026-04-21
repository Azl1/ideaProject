package com.abdullaevaziz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Очники
 */
public class FullTimeLearning extends Student {

    private List<Integer> listOfRatings = new ArrayList<>();

    public FullTimeLearning(String name, String family, int course) {
        super(name, family, course);
    }

    public List<Integer> getListOfRatings() {
        return listOfRatings;
    }

    public void setListOfRatings(List<Integer> listOfRatings) {
        this.listOfRatings = listOfRatings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullTimeLearning that = (FullTimeLearning) o;
        return Objects.equals(listOfRatings, that.listOfRatings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfRatings);
    }

    @Override
    public String toString() {
        return "FullTimeLearning{" +
                "listOfRatings=" + listOfRatings +
                '}';
    }

    public String toCSV(){
        return super.toCSV() + ";" + this.listOfRatings;
    }

    public void add(int rating){
        this.listOfRatings.add(rating);
    }

    public boolean isExcellent(){
        for (Integer value : this.listOfRatings) {
            if (value != 5){
                return false;
            }
        }
        return true;
    }


}
