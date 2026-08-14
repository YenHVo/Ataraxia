package com.ataraxia.backend.repository;

import com.ataraxia.backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.ataraxia.backend.enums.ReservationStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    List<Reservation> findByGuest_Id(Long guestId);

    List<Reservation> findByRoom_Id(Long roomId);

    List<Reservation> findByStatus(ReservationStatus status);

    @Query("""
        SELECT r FROM Reservation r
        WHERE r.room.id = :roomId
        AND r.checkInDate < :checkOutDate
        AND r.checkOutDate > :checkInDate
    """)
    List<Reservation> findOverlappingReservations(@Param("roomId") Long roomId, @Param("checkInDate") LocalDate checkInDate, @Param("checkOutDate") LocalDate checkOutDate);
}
