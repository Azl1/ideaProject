package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Host;
import com.abdullaevaziz.model.Neighbourhood;
import com.abdullaevaziz.model.Room;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Repository {
    private ArrayList<Room> roomArrayList = new ArrayList<>();
    private HashMap<String, String> boroughMap = new HashMap<>();

    public Repository() {
    }

    /**
     * 2. Загрузите весь набор данных (из обоих файлов) в память программы.
     * В итоге у вас должно быть несколько коллекций
     * (rooms, hosts, neighbourhoods и возможно boroughs)
     * разных размеров (например, количество hosts меньше количества rooms,
     * так как один host может предоставлять несколько rooms) с объектами,
     * правильно связанными друг с другом
     * (Room логически связан с Host и Neighbourhood). 3 балла
     */
    public Repository(String boroughsFile, String roomsFile) throws FileNotFoundException {
        loadBoroughs(boroughsFile);
        loadRooms(roomsFile);
    }

    private void loadBoroughs(String boroughsFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(boroughsFile))) {
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String[] split = line.split(";");
                    String neighbourhood = split[0];
                    String boroughs = split[1];
                    this.boroughMap.put(neighbourhood, boroughs);
                } catch (RuntimeException ignored) {
                }
            }
        } catch (IOException e) {
            System.out.println("Неверный формат файлов");
        }
    }

    private void loadRooms(String roomsFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(roomsFile))) {
            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String[] split = line.split(";");
                    long id = Long.parseLong(split[0]);
                    String name = split[1];
                    int hostId = Integer.parseInt(split[2]);
                    String hostName = split[3];
                    String neighbourhoodName = split[4];
                    String roomType = split[5];
                    String pr1 = split[6].replace("$", "")
                            .replace(",", ".").strip().replaceAll(" ", "");

                    double price = Double.parseDouble(pr1);
                    int minimumNights = Integer.parseInt(split[7]);
                    int numberOfReviews = Integer.parseInt(split[8]);
                    int daysOpen = Integer.parseInt(split[9]);

                    Host host = new Host(hostId, hostName);
                    String borough = this.boroughMap.getOrDefault(neighbourhoodName, "Неизвестно");
                    Neighbourhood neighbourhood = new Neighbourhood(neighbourhoodName, borough);
                    Room room = new Room(id, name, price, minimumNights,
                            numberOfReviews, roomType, host, neighbourhood, daysOpen);

                    this.roomArrayList.add(room);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Неверный формат файлов");
        }
    }

    /**
     * 3. Рассчитайте среднюю цену среди всех комнат в каждом borough.
     * Выведите результат на экран. 1.5 балла
     */
    public void averagePrice() {
        HashMap<String, ArrayList<Double>> boroughTotalPrice = new HashMap<>();

        for (Room room : this.roomArrayList) {
            String borough = room.getNeighbourhood().getBorough();
            double price = room.getPrice();
            ArrayList<Double> list = boroughTotalPrice.getOrDefault(borough, new ArrayList<>());
            list.add(price);
            boroughTotalPrice.put(borough, list);
        }

        for (var entry : boroughTotalPrice.entrySet()) {
            double valAvg = entry.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0);
            System.out.println(entry.getKey() + " : " + valAvg);
        }
    }

    /**
     * 4. Реализовать интерактивный поиск комнат для пользователя.
     * Пользователь вводит параметры поиска:
     * •	Название боро
     * •	Тип комнаты (одно из 3-х значений: private room, shared room, entire home/apt)
     * •	Количество ночей (фильтровать только те комнаты, где minimum nights <= введённое значение)
     * Программа должна найти подходящие комнаты и вывести их в порядке возрастания общей стоимости, где:
     * общая стоимость = цена за ночь × количество ночей
     * Для каждой подходящей комнаты вывести:
     * •	Название комнаты
     * •	Имя хозяина
     * •	Цена за ночь
     * •	Общая стоимость
     * 🟢 2 балла.
     * ________________________________________
     */
    public void searchRoom(String boro, String roomType, int countRoom) {
        List<Room> list = new ArrayList<>();
        for (Room room : roomArrayList) {
            list.add(room);
        }
        list.stream().
                filter(x -> x.getNeighbourhood().getBorough().equals(boro)).
                filter(x -> x.getRoomType().equals(roomType)).
                filter(x -> x.getMinimumNights()<= countRoom).collect(Collectors.toList());

                long totalCost = list.stream().map(x-> x.getPrice() * x.getMinimumNights()).count();

       Comparator<Room> comparator = new Comparator<Room>() {
           @Override
           public int compare(Room o1, Room o2) {
               return (int) (o1.getPrice() * o2.getMinimumNights());
           }
       };
       list.sort(comparator);
        for (Room room : list) {
        System.out.println("Название комнаты: " + room.getName());
        System.out.println("Имя хозяина: " + room.getHost().getName());
        System.out.println("Цена за ночь " + room.getPrice());
        System.out.println("Общая стоимость " + totalCost + "\n");
        }

    }

    @Override
    public String toString() {
        return "Repository{" +
                "roomArrayList=" + roomArrayList + "\n" +
                ", boroughMap=" + boroughMap +
                '}';
    }
}
