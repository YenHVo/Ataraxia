package com.ataraxia.backend.repository;
import com.ataraxia.backend.entity.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.ataraxia.backend.enums.TransactionType;

import java.time.LocalDate;


public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {
    
    List<InventoryTransaction> findByEmployee_Id(Long employeeId);

    List<InventoryTransaction> findByInventoryItem_Id(Long inventoryItemId);

    List<InventoryTransaction> findByTransactionType(TransactionType transactionType);

    @Query("SELECT it FROM InventoryTransaction it WHERE it.transactionDate BETWEEN :startDate AND :endDate")
    List<InventoryTransaction> findTransactionsWithinDateRange(LocalDate startDate, LocalDate endDate);
}
