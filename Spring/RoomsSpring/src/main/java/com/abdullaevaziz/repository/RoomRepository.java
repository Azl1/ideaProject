package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Host;
import com.abdullaevaziz.model.Neighbourhood;
import com.abdullaevaziz.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room  findRoomByHost_IdAndNeighbourhood_Id(long host_id, long neighbourhood_Id);

    List<Room> findRoomsByNeighbourhood_BoroughAndRoomTypeAndMinimumNights(String boro, String roomType, int countRoom);

}
