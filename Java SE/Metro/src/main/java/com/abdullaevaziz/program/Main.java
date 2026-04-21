package com.abdullaevaziz.program;

import com.abdullaevaziz.util.Line;
import com.abdullaevaziz.util.Repository;
import com.abdullaevaziz.util.Station;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        try {
            Repository repository = new Repository("input.txt");
            System.out.println(repository);

            System.out.println();
            System.out.println("1--------------------------------------");
            ArrayList<Station> res1 = repository.maxTraffic();
            System.out.println(res1);

            System.out.println();
            System.out.println("2--------------------------------------");
            HashMap<Line, Integer> res2 = repository.sumTraffic();
            System.out.println(res2);

            System.out.println();
            System.out.println("3--------------------------------------");
            ArrayList<Station> res3 = repository.sortByStationName();
            System.out.println(res3);

            System.out.println();
            repository.save("out.txt");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}