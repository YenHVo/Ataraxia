package com.ataraxia.backend.repository;

import com.ataraxia.backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.ataraxia.backend.enums.ReservationStatus;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    List<Reservation> findByGuest_Id(Long guestId);

    List<Reservation> findByRoom_Id(Long roomId);

    List<Reservation> findByStatus(ReservationStatus status);
}
