package com.ataraxia.backend.service;

import com.ataraxia.backend.entity.Reservation;
import com.ataraxia.backend.repository.ReservationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ataraxia.backend.enums.ReservationStatus;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservation) {
        this.reservationRepository = reservation;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Reservation not found with id: " + id
        ));
    }

    public List<Reservation> getReservationsByGuestId(Long guestId) {
        return reservationRepository.findByGuest_Id(guestId);
    }

    public List<Reservation> getReservationsByRoomId(Long roomId) {
        return reservationRepository.findByRoom_Id(roomId);
    }

    public List<Reservation> getReservationsByStatus(ReservationStatus status) {
        return reservationRepository.findByStatus(status);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        if (reservationRepository.findById(reservation.getId()).isPresent()) {
            throw new RuntimeException("Reservation already exists");
        }
        
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Reservation existingReservation = reservationRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Reservation not found with id: " + id
            ));

        existingReservation.setGuest(updatedReservation.getGuest());
        existingReservation.setRoom(updatedReservation.getRoom());
        existingReservation.setCheckInDate(updatedReservation.getCheckInDate());
        existingReservation.setCheckOutDate(updatedReservation.getCheckOutDate());
        existingReservation.setStatus(updatedReservation.getStatus());
        existingReservation.setTotalPrice(updatedReservation.getTotalPrice());
        existingReservation.setNumberOfGuests(updatedReservation.getNumberOfGuests());

        return reservationRepository.save(existingReservation);
    }
    
}
