package com.ataraxia.backend.controller;

import com.ataraxia.backend.dto.ReservationRequest;
import com.ataraxia.backend.entity.Reservation;
import com.ataraxia.backend.service.ReservationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.ataraxia.backend.enums.ReservationStatus;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:5173")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @GetMapping("/guest/{guestId}")
    public List<Reservation> getReservationsByGuestId(@PathVariable Long guestId) {
        return reservationService.getReservationsByGuestId(guestId);
    }

    @GetMapping("/room/{roomId}")
    public List<Reservation> getReservationsByRoomId(@PathVariable Long roomId) {
        return reservationService.getReservationsByRoomId(roomId);
    }

    @GetMapping("/status/{status}")
    public List<Reservation> getReservationsByStatus(@PathVariable ReservationStatus status) {
        return reservationService.getReservationsByStatus(status);
    }

    @PostMapping
    public Reservation createReservation(@Valid @RequestBody ReservationRequest request) {
        return reservationService.createReservation(request);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        return reservationService.updateReservation(id, updatedReservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
