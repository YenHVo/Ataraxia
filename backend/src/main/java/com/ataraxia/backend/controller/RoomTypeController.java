package com.ataraxia.backend.controller;

import com.ataraxia.backend.entity.RoomType;
import com.ataraxia.backend.service.RoomTypeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/room-types")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomTypeController {
    
    private final RoomTypeService roomTypeService;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @GetMapping
    public List<RoomType> getAllRoomTypes() {
        return roomTypeService.getAllRoomTypes();
    }

    @PostMapping
    public RoomType createRoomType(@RequestBody @Valid RoomType roomType) {
        return roomTypeService.createRoomType(roomType);
    }

    @GetMapping("/{id}")
    public RoomType getRoomTypeById(@PathVariable Long id) {
        return roomTypeService.getRoomTypeById(id);
    }

    @GetMapping("/name/{name}")
    public RoomType getRoomTypeByName(@PathVariable String name) {
        return roomTypeService.getRoomTypeByName(name);
    }

    @GetMapping("/capacity/{capacity}")
    public List<RoomType> getRoomTypeByCapacity(@PathVariable int capacity) {
        return roomTypeService.getRoomTypeByCapacity(capacity);
    }

    @PutMapping("/{id}")
    public RoomType updateRoomType(@PathVariable Long id, @RequestBody @Valid RoomType roomType) {
        return roomTypeService.updateRoomType(id, roomType);
    }

    @DeleteMapping("/{id}")
    public void deleteRoomType(@PathVariable Long id) {
        roomTypeService.deleteRoomType(id);
    }
}
