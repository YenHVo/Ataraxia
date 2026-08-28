package com.ataraxia.backend.repository;

import com.ataraxia.backend.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    Optional<Guest> findByEmail(String email);

    List<Guest> findByLastNameContainingIgnoreCase(String lastName);

}
