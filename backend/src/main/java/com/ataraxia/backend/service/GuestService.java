package com.ataraxia.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ataraxia.backend.entity.Guest;
import com.ataraxia.backend.repository.GuestRepository;
import java.util.List;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest getGuestById(Long id) {
        return guestRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Guest not found with id: " + id
        ));
    }

    public Guest getGuestByEmail(String email) {
        return guestRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Guest not found with email: " + email
        ));
    }

    public Guest createGuest(Guest guest) {
        if (guestRepository.findByEmail(guest.getEmail()).isPresent()) {
            throw new RuntimeException("Guest already exists");
        }

        return guestRepository.save(guest);
    }

    public List<Guest> getGuestsByLastName(String lastName) {
        return guestRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public Guest updateGuest(Long id, Guest updatedGuest) {
        Guest existingGuest = guestRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Guest not found with id: " + id
            ));

        if (!existingGuest.getEmail().equals(updatedGuest.getEmail())) {
            if (guestRepository.findByEmail(updatedGuest.getEmail()).isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Email is already in use: " + updatedGuest.getEmail()
                );
            }
        }

        existingGuest.setFirstName(updatedGuest.getFirstName());
        existingGuest.setLastName(updatedGuest.getLastName());
        existingGuest.setEmail(updatedGuest.getEmail());
        existingGuest.setPhone(updatedGuest.getPhone());
        existingGuest.setAddress(updatedGuest.getAddress());

        return guestRepository.save(existingGuest);
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }
    
}
