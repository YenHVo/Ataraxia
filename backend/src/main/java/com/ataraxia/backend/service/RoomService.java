package com.ataraxia.backend.service;

import com.ataraxia.backend.entity.Room;
import com.ataraxia.backend.repository.RoomRepository;
import com.ataraxia.backend.repository.RoomTypeRepository;
import com.ataraxia.backend.entity.RoomType;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ataraxia.backend.enums.RoomStatus;

import java.util.List;

@Service
public class RoomService {
    
    private final RoomRepository roomRepository;

    private final RoomTypeRepository roomTypeRepository;

    public RoomService(RoomRepository roomRepository, RoomTypeRepository roomTypeRepository) {
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Room not found with id: " + id
        ));
    }

    public Room getRoomByRoomNumber(String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Room not found with room number: " + roomNumber
        ));
    }

    public List<Room> getRoomsByStatus(RoomStatus status) {
        return roomRepository.findByStatus(status);
    }

    public List<Room> getRoomsByType(String type) {
        return roomRepository.findByRoomType(type);
    }

    public List<Room> getRoomsByFloor(int floor) {
        return roomRepository.findByFloor(floor);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public Room createRoom(Room room) {
        if (roomRepository.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new RuntimeException("Room already exists");
        }

        RoomType roomType = roomTypeRepository.findById(room.getRoomType().getId())
                .orElseThrow(() -> new RuntimeException("Room Type not found"));

        room.setRoomType(roomType);
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room updatedRoom) {
        Room existingRoom = roomRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Room not found with id: " + id
            ));

        existingRoom.setRoomNumber(updatedRoom.getRoomNumber());
        existingRoom.setFloor(updatedRoom.getFloor());
        existingRoom.setStatus(updatedRoom.getStatus());
        existingRoom.setRoomType(updatedRoom.getRoomType());

        return roomRepository.save(existingRoom);
    }
}
