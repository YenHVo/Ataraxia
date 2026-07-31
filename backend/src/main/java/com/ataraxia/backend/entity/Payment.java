package com.ataraxia.backend.entity;

import jakarta.persistence.*;
import com.ataraxia.backend.enums.PaymentMethod;
import com.ataraxia.backend.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "payments")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String method;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private PaymentStatus paymentStatus;

    @Column (nullable = false, updatable = false)
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    public Payment() {
    }

    public Payment(BigDecimal amount, PaymentMethod method, Reservation reservation, PaymentStatus status) {
        this.amount = amount;
        this.paymentMethod = method;
        this.reservation = reservation;
        this.paymentStatus = status;
    }

    @PrePersist
    protected void onCreate() {
        this.paymentDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod method) {
        this.paymentMethod = method;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus status) {
        this.paymentStatus = status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment that = (Payment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", method='" + paymentMethod + '\'' +
                ", status='" + paymentStatus + '\'' +
                ", paymentDate=" + paymentDate +
                ", reservation=" + reservation +
                '}';
    }
}
