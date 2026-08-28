package com.ataraxia.backend.service;

import com.ataraxia.backend.entity.RoomType;
import com.ataraxia.backend.repository.RoomTypeRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public RoomType getRoomTypeByName(String name) {
        return roomTypeRepository.findByNameContainingIgnoreCase(name).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Room type not found with name: " + name
        ));
    }

    public RoomType getRoomTypeById(Long id) {
        return roomTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Room type not found with id: " + id
        ));
    }

    public List<RoomType> getRoomTypeByCapacity(int capacity) {
        return roomTypeRepository.findByCapacity(capacity);
    }

    public RoomType createRoomType(RoomType roomType) {
        if (roomTypeRepository.findByNameContainingIgnoreCase(roomType.getName()).isPresent()) {
            throw new RuntimeException("Room type already exists");
        }
        
        return roomTypeRepository.save(roomType);
    }

    public RoomType updateRoomType(Long id, RoomType updatedRoomType) {
        RoomType existingRoomType = roomTypeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Room type not found with id: " + id
            ));

        if (!existingRoomType.getName().equals(updatedRoomType.getName())) {
            if (roomTypeRepository.findByNameContainingIgnoreCase(updatedRoomType.getName()).isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Room name is already in use: " + updatedRoomType.getName()
                );
            }
        }

        existingRoomType.setName(updatedRoomType.getName());
        existingRoomType.setBasePrice(updatedRoomType.getBasePrice());
        existingRoomType.setCapacity(updatedRoomType.getCapacity());
        existingRoomType.setDescription(updatedRoomType.getDescription());

        return roomTypeRepository.save(existingRoomType);
}

    public void deleteRoomType(Long id) {
        roomTypeRepository.deleteById(id);
    }
}
