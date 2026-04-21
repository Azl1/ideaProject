package com.abdullaevaziz.main;


import com.abdullaevaziz.model2.BoardingData;
import com.abdullaevaziz.repository.BoardingDataRepository;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {


        /**
         * 3. Сгенерировать через специальный сервис модель данных,
         * отражающую объекты из файла FrequentFlyerForum-Profiles.json.
         * Начальный класс имеет название: FrequentFlyer.
         */
        /*Json2PojoGenerator generator1 = new Json2PojoGenerator("FrequentFlyerForum-Profiles.json",
                "src/main/java/");
        generator1.generate("FrequentFlyer", "com.abdullaevaziz.example");*/


        /*Json2PojoGenerator generator2 = new Json2PojoGenerator("BoardingData.csv",
                "src/main/java/");
        generator2.generate("BoardingData", "com.abdullaevaziz.example2");*/

        /**
         * 6. Для работы с датами использовать класс LocalDate
         */
        try {
            /*FrequentFlyer frequentFlyer = new FrequentFlyer();
            Repository repository1 = new Repository("FrequentFlyerForum-Profiles.json");
            System.out.println(repository1);*/

            /*System.out.println("1----------------------------------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------------------------");
            HashSet<RealName> registeredFlightList1 = repository1.searchPassengers("Moscow");
            System.out.println(registeredFlightList1);*/

            /*System.out.println("2----------------------------------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------------------------");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            HashSet<RealName> registeredFlightList2 = repository1.searchPassengersData(LocalDate.parse("05.07.2017", dateTimeFormatter));
            System.out.println(registeredFlightList2);*/

            BoardingData boardingData = new BoardingData();

            BoardingDataRepository boardingDataRepository1 = new BoardingDataRepository("BoardingData.csv");
            //  boardingDataRepository1.add(boardingData);
            //boardingDataRepository1.saveCSV("BoardingData.json");
            boardingDataRepository1.save("BoardingData.json");


            System.out.println(boardingDataRepository1);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}