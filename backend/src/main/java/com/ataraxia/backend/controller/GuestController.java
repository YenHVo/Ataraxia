package com.ataraxia.backend.controller;

import org.springframework.web.bind.annotation.*;
import com.ataraxia.backend.entity.Guest;
import com.ataraxia.backend.service.GuestService;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/guests")
@CrossOrigin(origins = "http://localhost:5173")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public List<Guest> getAllGuests() {
        return guestService.getAllGuests();
    }

    @GetMapping("/{id}")
    public Guest getGuestById(@PathVariable Long id) {
        return guestService.getGuestById(id);
    }

    @GetMapping("/email/{email}")
    public Guest getGuestByEmail(@PathVariable String email) {
        return guestService.getGuestByEmail(email);
    }

    @GetMapping("/lastname/{lastName}")
    public List<Guest> getGuestsByLastName(@PathVariable String lastName) {
        return guestService.getGuestsByLastName(lastName);
    }

    @PostMapping
    public Guest createGuest(@RequestBody @Valid Guest guest) {
        return guestService.createGuest(guest);
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable Long id, @RequestBody @Valid Guest updatedGuest) {
        return guestService.updateGuest(id, updatedGuest);
    }

    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
    }
    
}
