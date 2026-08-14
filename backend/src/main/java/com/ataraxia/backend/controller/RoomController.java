package com.ataraxia.backend.controller;

import com.ataraxia.backend.entity.Room;
import com.ataraxia.backend.service.RoomService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.ataraxia.backend.enums.RoomStatus;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/number/{roomNumber}")
    public Room getRoomByRoomNumber(@PathVariable String roomNumber) {
        return roomService.getRoomByRoomNumber(roomNumber);
    }

    @GetMapping("/type/{roomType_id}")
    public List<Room> getRoomsByType(@PathVariable Long roomType_id) {
        return roomService.getRoomsByType(roomType_id);
    }

    @GetMapping("/status/{status}")
    public List<Room> getRoomsByStatus(@PathVariable RoomStatus status) {
        return roomService.getRoomsByStatus(status);
    }

    @GetMapping("/floor/{floor}")
    public List<Room> getRoomsByFloor(@PathVariable int floor) {
        return roomService.getRoomsByFloor(floor);
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room updatedRoom) {
        return roomService.updateRoom(id, updatedRoom);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}