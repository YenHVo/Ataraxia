package com.ataraxia.backend.controller;

import org.springframework.web.bind.annotation.*;
import com.ataraxia.backend.service.PaymentService;
import com.ataraxia.backend.entity.Payment;
import java.util.List;

import com.ataraxia.backend.enums.PaymentStatus;


@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {
    
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/reservation/{reservationId}")
    public Payment getPaymentByReservationId(@PathVariable Long reservationId) {
        return paymentService.getPaymentByReservationId(reservationId);
    }

    @GetMapping("/status/{status}")
    public List<Payment> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        return paymentService.getPaymentsByStatus(status);
    }
    

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
        return paymentService.updatePayment(id, updatedPayment);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }

}
