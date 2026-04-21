package com.kirillkotov.program;

import com.kirillkotov.model.Size;
import com.kirillkotov.model.TV;
import com.kirillkotov.repository.TVArrayRepository;
import com.kirillkotov.repository.TVRepository;
import com.kirillkotov.repository.TVSingleRepository;
import com.kirillkotov.util.Json2PojoGenerator;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        try {
            TV tv1 = new TV("Samsung", "K900", "Black", 10, 10000,
                    simpleDateFormat.parse("14.11.1990 10:11:12"),
                    LocalDateTime.parse("14.11.1990 10:11:12", dateTimeFormatter),
                    new Size(1, 2, 3));
            TV tv2 = new TV("Samsung", "K900", "Black", 10, 10000,
                    simpleDateFormat.parse("14.11.1995 10:16:12"),
                    LocalDateTime.parse("14.11.1990 10:11:12", dateTimeFormatter),
                    new Size(4, 5, 6));
            System.out.println("Print tv1 and tv2\n" + tv1 + "\n" + tv2);

            //Save tv1 to tv1.json
            TVSingleRepository tvSingleRepository1 = new TVSingleRepository(tv1);
            tvSingleRepository1.save("tv1.json");

            //Load tv from tv1.json
            TVSingleRepository tvSingleRepository2 = new TVSingleRepository("tv1.json");
            System.out.println("\nPrint TVSingleRepository after load\n" + tvSingleRepository2);
            TV res1 = tvSingleRepository2.getTv();
            System.out.println("Print tv after load\n" + res1);

            //Save ArrayList of TV to tvs1.json
            TVRepository tvRepository1 = new TVRepository();
            tvRepository1.add(tv1);
            tvRepository1.add(tv2);
            tvRepository1.save("tvs1.json");

            //Load ArrayList of TV from tvs1.json
            TVRepository tvRepository2 = new TVRepository("tvs1.json");
            System.out.println("\nPrint ArrayList of TV after load\n" + tvRepository2);

            //Save Array of TV to tvs2.json
            TVArrayRepository tvArrayRepository1 = new TVArrayRepository();
            tvArrayRepository1.add(tv1);
            tvArrayRepository1.add(tv2);
            tvArrayRepository1.save("tvs2.json");

            //Load Array of TV from tvs2.json
            TVArrayRepository tvArrayRepository = new TVArrayRepository("tvs2.json");
            System.out.println("\nPrint Array of TV after load\n" + tvArrayRepository);

            //Generate Schema from JSON file
            Json2PojoGenerator generator = new Json2PojoGenerator("tvs1.json",
                    "src/main/java/");
            generator.generate("TV", "com.kirillkotov.example");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}