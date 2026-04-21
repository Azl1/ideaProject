package com.abdullaevaziz.repository;

import com.abdullaevaziz.example.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Repository {
    FrequentFlyer frequentFlyer;


    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }


    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 4. Создать репозиторий, конструктор которого
     * принимает путь к файлу и производит инициализацию поля репозитория
     */

    public Repository() {
    }

    public Repository(FrequentFlyer frequentFlyer) {
        this.frequentFlyer = frequentFlyer;
    }

    public Repository(String fileName) throws IOException {
        this.frequentFlyer = objectMapper.readValue(new File(fileName), FrequentFlyer.class);
    }


    /**
     * 7. Найти всех пассажиров, которые вылетали из указанного города,
     * передав его как параметр метода
     */
    public HashSet<RealName> searchPassengers(String city){
        HashSet<RealName> passengersFromCity = new HashSet<>();
        for (ForumProfile passenger : this.frequentFlyer.getForumProfiles()) {
            List<RegisteredFlight> registeredFlights = passenger.getRegisteredFlights();
            for (RegisteredFlight registeredFlight : registeredFlights) {
               if (registeredFlight.getDeparture().getCity().equals(city)) {
                    passengersFromCity.add(passenger.getRealName());
                }
            }
        }
        return passengersFromCity;

    }

    /**
     * 8. Найти всех пассажиров, которые вылетали позднее указанной даты, передав ее как параметр метода
     */
    public HashSet<RealName> searchPassengersData(LocalDate date){
        HashSet<RealName> passengersFromCityData = new HashSet<>();
        for (ForumProfile passenger : this.frequentFlyer.getForumProfiles()) {
            List<RegisteredFlight> registeredFlights = passenger.getRegisteredFlights();
            for (RegisteredFlight registeredFlight : registeredFlights) {
                if (registeredFlight.getDate().isAfter(date)) {
                    passengersFromCityData.add(passenger.getRealName());
                }
            }
        }
        return passengersFromCityData;
    }



    @Override
    public String toString() {
        return "Repository{" +
                "forumProfiles=" + frequentFlyer +
                ", objectMapper=" + objectMapper +
                '}';
    }
}
