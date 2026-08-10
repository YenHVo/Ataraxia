package com.ataraxia.backend.entity;
import jakarta.persistence.*;
import java.util.Objects;
import com.ataraxia.backend.enums.TransactionType;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_transactions")
public class InventoryTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private InventoryItem inventoryItem;

    @Column(nullable = false)
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false, updatable = false)
    private LocalDateTime transactionDate;

    @Column
    private String notes;

    public InventoryTransaction() {
    }

    public InventoryTransaction(InventoryItem inventoryItem, int quantity, TransactionType transactionType, Employee employee, String notes) {
        this.inventoryItem = inventoryItem;
        this.quantity = quantity;
        this.transactionType = transactionType;
        this.employee = employee;
        this.notes = notes;
    }

    @PrePersist
    protected void onCreate() {
        transactionDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryTransaction)) return false;
        InventoryTransaction that = (InventoryTransaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "InventoryTransaction{" +
                "id=" + id +
                ", inventoryItem=" + inventoryItem +
                ", quantity=" + quantity +
                ", transactionType=" + transactionType +
                ", employee=" + employee +
                ", transactionDate=" + transactionDate +
                ", notes='" + notes + '\'' +
                '}';
    }

}
