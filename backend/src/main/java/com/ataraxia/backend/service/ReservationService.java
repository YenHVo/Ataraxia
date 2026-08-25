package com.ataraxia.backend.service;

import com.ataraxia.backend.entity.Guest;
import com.ataraxia.backend.entity.Reservation;
import com.ataraxia.backend.entity.Room;
import com.ataraxia.backend.repository.ReservationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ataraxia.backend.enums.ReservationStatus;
import com.ataraxia.backend.repository.GuestRepository;
import com.ataraxia.backend.repository.RoomRepository;
import com.ataraxia.backend.repository.RoomTypeRepository;
import com.ataraxia.backend.entity.RoomType;
import java.time.LocalDate;
import com.ataraxia.backend.dto.ReservationRequest;
import java.util.List;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final GuestRepository guestRepository;

    private final RoomRepository roomRepository;

    private final RoomTypeRepository roomTypeRepository;

    public ReservationService(ReservationRepository reservationRepository, GuestRepository guestRepository, RoomRepository roomRepository, RoomTypeRepository roomTypeRepository) {
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
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

    public BigDecimal calculateTotalPrice(RoomType roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        long numberOfNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return roomType.getBasePrice().multiply(BigDecimal.valueOf(numberOfNights));
    }

    public Room findAvailableRoom(Long roomTypeId, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> rooms = roomRepository.findByRoomType_Id(roomTypeId);

        for (Room room : rooms) {
            List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(room.getId(), checkInDate, checkOutDate);
            if (overlappingReservations.isEmpty()) {
                return room; // Return the first available room
            }
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No available rooms found for the specified type and dates"
        );
    }

    public Reservation updateReservationStatus(Long id, ReservationStatus newStatus) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Reservation not found with id: " + id
                ));

        ReservationStatus currentStatus = reservation.getStatus();
        if (!isValidStatusTransition(currentStatus, newStatus)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid status transition from " + currentStatus + " to " + newStatus
            );
        }

        reservation.setStatus(newStatus);
        return reservationRepository.save(reservation);
    }

    private boolean isValidStatusTransition(ReservationStatus currentStatus, ReservationStatus newStatus) {
        switch (currentStatus) {
            case PENDING:
                return newStatus == ReservationStatus.CONFIRMED || newStatus == ReservationStatus.CANCELLED;
            case CONFIRMED:
                return newStatus == ReservationStatus.CHECKED_IN || newStatus == ReservationStatus.CANCELLED;
            case CHECKED_IN:
                return newStatus == ReservationStatus.COMPLETED;
            case COMPLETED:
                return false; 
            case CANCELLED:
                return false; 
            default:
                return false;
        }
    }

    public Reservation createReservation(ReservationRequest request) {
        if (!request.getCheckInDate().isBefore(request.getCheckOutDate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Check-in date must be before check-out date"
            );
        }

        Guest guest = guestRepository.findById(request.getGuestId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Guest not found with id: " + request.getGuestId()
                ));

        RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Room Type not found with id: " + request.getRoomTypeId()
                ));

        Room availableRoom = findAvailableRoom(roomType.getId(), request.getCheckInDate(), request.getCheckOutDate());
        BigDecimal totalPrice = calculateTotalPrice(roomType, request.getCheckInDate(), request.getCheckOutDate());

        Reservation reservation = new Reservation();
        reservation.setGuest(guest);
        reservation.setRoom(availableRoom);
        reservation.setCheckInDate(request.getCheckInDate());
        reservation.setCheckOutDate(request.getCheckOutDate());
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setNumberOfGuests(request.getNumberOfGuests());
        reservation.setTotalPrice(totalPrice);

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