package com.ataraxia.backend.repository;

import com.ataraxia.backend.entity.Payment;
import com.ataraxia.backend.enums.PaymentStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByReservationId(Long reservationId);
    
    List<Payment> findByStatus(PaymentStatus status);
}
