package com.ataraxia.backend.repository;

import com.ataraxia.backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ataraxia.backend.enums.RoomStatus;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumber(String roomNumber);
    
    List<Room> findByRoomType(String roomType);

    List<Room> findByStatus(RoomStatus status);

    List<Room> findByFloor(Integer floor);
}
