package com.ataraxia.backend.service;

import com.ataraxia.backend.entity.RoomType;
import com.ataraxia.backend.repository.RoomTypeRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoomTypeService {

    public final RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public RoomType saveRoomType(RoomType roomType) {
        if (roomTypeRepository.findByName(roomType.getName()).isPresent()) {
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

        existingRoomType.setName(updatedRoomType.getName());
        existingRoomType.setBasePrice(updatedRoomType.getBasePrice());
        existingRoomType.setCapacity(updatedRoomType.getCapacity());
        existingRoomType.setDescription(updatedRoomType.getDescription());

        return roomTypeRepository.save(existingRoomType);
}

    public RoomType getRoomTypeById(Long id) {
        return roomTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Room type not found with id: " + id
        ));
    }

    public void deleteRoomType(Long id) {
        roomTypeRepository.deleteById(id);
    }
}
