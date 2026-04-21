package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Host;
import com.abdullaevaziz.model.Neighbourhood;
import com.abdullaevaziz.model.Room;
import com.abdullaevaziz.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Value("${datasource.filename.rooms}")
    private String fileName;

    private RoomRepository roomRepository;

    private HostService hostService;
    private NeighbourhoodService neighbourhoodService;

    @Autowired
    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Autowired
    public void setHostService(HostService hostService) {
        this.hostService = hostService;
    }


    @Autowired
    public void setNeighbourhoodService(NeighbourhoodService neighbourhoodService) {
        this.neighbourhoodService = neighbourhoodService;
    }


    @PostConstruct
    public void init() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String[] split = line.split(";");
                    long id = Long.parseLong(split[0]);
                    String name = split[1];
                    String hostName = split[3];
                    String neighbourhoodName = split[4];
                    String roomType = split[5];
                    String pr1 = split[6].replace("$", "")
                            .replace(",", ".").strip().replaceAll(" ", "");

                    double price = Double.parseDouble(pr1);
                    int minimumNights = Integer.parseInt(split[7]);
                    int numberOfReviews = Integer.parseInt(split[8]);
                    int daysOpen = Integer.parseInt(split[9]);

                    Host hostGet = this.hostService.findByName(hostName);

                    Neighbourhood neighbourhoodGet = this.neighbourhoodService.findByName(neighbourhoodName);

                    Room room = new Room(id, name, price, minimumNights,
                            numberOfReviews, roomType, hostGet, neighbourhoodGet, daysOpen);
                    this.add(room, hostGet.getId(), neighbourhoodGet.getId());

                } catch (RuntimeException ignored) {
                }
            }
        } catch (IOException e) {
            System.out.println("Неверный формат файлов");
        }

    }


    @Override
    public Room add(Room room, long host_id, long neighbourhood_id) {

        Host hostGet = this.hostService.get(host_id);

        Neighbourhood neighbourhoodGet = this.neighbourhoodService.get(neighbourhood_id);

        Room roomGet = this.roomRepository.findRoomByHost_IdAndNeighbourhood_Id(hostGet.getId(), neighbourhoodGet.getId());
        if (roomGet != null) {
            throw new IllegalArgumentException("Room already exists for this host and neighbourhood!");
        }

        room.setHost(hostGet);
        room.setNeighbourhood(neighbourhoodGet);
        try {
            return this.roomRepository.save(room);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Room has already added!");
        }

    }

    @Override
    public Room get(long id) {
        return this.roomRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Room does not exists!"));
    }

    @Override
    public Room get(long host_id, long neighbourhood_id) {
        return this.roomRepository.findRoomByHost_IdAndNeighbourhood_Id(host_id, neighbourhood_id);
    }

    @Override
    public List<Room> findAll() {
        return this.roomRepository.findAll();
    }

    @Override
    public void exportToCSV(String outputFileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            bufferedWriter.write("id;name;host_id;host_name;neighbourhood;room_type; price ;minimum_nights;number_of_reviews;days open\n");
            for (Room room : this.findAll()) {
                bufferedWriter.write(room.toCSV());
                bufferedWriter.newLine();
            }
        }
    }

    @Override
    public Map<String, Double> averagePrice() {
        Map<String, Double> boroughTotalPriceMap = new HashMap<>();

        for (Room room : this.findAll()) {
            String borough = room.getNeighbourhood().getBorough();
            double price = room.getPrice();
            boroughTotalPriceMap.put(borough, price);
        }
        return boroughTotalPriceMap;
    }

    @Override
    public List<Room> searchRoom(String boro, String roomType, int countRoom) {

        List<Room> filteredRooms = this.roomRepository.findRoomsByNeighbourhood_BoroughAndRoomTypeAndMinimumNights(boro, roomType, countRoom);

        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return (int) (o1.getPrice() * o2.getMinimumNights());
            }
        };
        filteredRooms.sort(comparator);
        return filteredRooms;
    }
}
