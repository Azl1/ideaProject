package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface RoomService {

    Room add(Room room, long host_id, long neighbourhood_id);
    Room get(long id);
    Room get(long host_id, long neighbourhood_id);

    List<Room> findAll();
    void exportToCSV(String outputFileName) throws IOException;

    Map<String, Double> averagePrice();
    List<Room> searchRoom(String boro, String roomType, int countRoom);
}
