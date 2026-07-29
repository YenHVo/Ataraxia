package com.ataraxia.backend.controller;

import com.ataraxia.backend.entity.RoomType;
import com.ataraxia.backend.service.RoomTypeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public RoomType createRoomType(@RequestBody RoomType roomType) {
        return roomTypeService.saveRoomType(roomType);
    }

    @GetMapping("/{id}")
    public RoomType getRoomTypeById(@PathVariable Long id) {
        return roomTypeService.getRoomTypeById(id);
    }

    @PutMapping("/{id}")
    public RoomType updateRoomType(@PathVariable Long id, @RequestBody RoomType roomType) {
        return roomTypeService.updateRoomType(id, roomType);
    }

    @DeleteMapping("/{id}")
    public void deleteRoomType(@PathVariable Long id) {
        roomTypeService.deleteRoomType(id);
    }
}
