package com.ataraxia.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ataraxia.backend.entity.Payment;
import com.ataraxia.backend.entity.Reservation;
import com.ataraxia.backend.repository.PaymentRepository;
import com.ataraxia.backend.enums.PaymentStatus;
import com.ataraxia.backend.repository.ReservationRepository;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    public PaymentService(PaymentRepository paymentRepository, ReservationRepository reservationRepository) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Payment not found with id: " + id
        ));
    }

    public Payment getPaymentByReservationId(Long reservationId) {
        return paymentRepository.findByReservationId(reservationId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Payment not found for reservation id: " + reservationId
                ));
    }

    public List<Payment> getPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    public Payment createPayment(Payment payment) {
        Reservation reservation = reservationRepository.findById(payment.getReservation().getId())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Reservation not found with id: " + payment.getReservation().getId()
                ));

        payment.setReservation(reservation);
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public Payment updatePayment(Long id, Payment updatedPayment) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Payment not found with id: " + id
                )); 


        Reservation reservation = reservationRepository.findById(updatedPayment.getReservation().getId())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Reservation not found with id: " + updatedPayment.getReservation().getId()
                ));

        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());
        existingPayment.setPaymentStatus(updatedPayment.getPaymentStatus());
        existingPayment.setReservation(reservation); 

        return paymentRepository.save(existingPayment);
    }

}
