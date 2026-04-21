package com.kirillkotov;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirillkotov.model.Car;
import com.kirillkotov.model.Truck;
import com.kirillkotov.model.Vehicle;
import com.kirillkotov.repository.VehicleRepository;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Mercedes-Benz", "S500", 5, 250.0);
        Truck truck = new Truck("Isuzu", "NQR", 7500.0);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonDataString = objectMapper.writeValueAsString(car);
            System.out.println(jsonDataString);

            Vehicle deserializedVehicle = objectMapper.readValue(jsonDataString, Vehicle.class);
            System.out.println(deserializedVehicle.getClass());
            System.out.println(deserializedVehicle);

            Car deserializedCar = objectMapper.readValue(jsonDataString, Car.class);
            System.out.println(deserializedCar);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try {
            VehicleRepository vehicleRepository = new VehicleRepository();
            vehicleRepository.add(car);
            vehicleRepository.add(truck);
            vehicleRepository.save("vehicles.json");

            VehicleRepository from = new VehicleRepository("vehicles.json");
            List<Vehicle> data = from.getData();
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}