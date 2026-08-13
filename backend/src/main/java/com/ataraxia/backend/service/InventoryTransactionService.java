package com.ataraxia.backend.service;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ataraxia.backend.entity.InventoryTransaction;
import com.ataraxia.backend.repository.InventoryTransactionRepository;
import com.ataraxia.backend.entity.Employee;
import com.ataraxia.backend.repository.EmployeeRepository;
import com.ataraxia.backend.entity.InventoryItem;
import com.ataraxia.backend.repository.InventoryItemRepository;
import java.util.List;
import com.ataraxia.backend.enums.TransactionType;
import java.time.LocalDate;

@Service
public class InventoryTransactionService {
    
    private final InventoryTransactionRepository inventoryTransactionRepository;

    private final EmployeeRepository employeeRepository;

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryTransactionService(InventoryTransactionRepository inventoryTransactionRepository, EmployeeRepository employeeRepository, InventoryItemRepository inventoryItemRepository) {
        this.inventoryTransactionRepository = inventoryTransactionRepository;
        this.employeeRepository = employeeRepository;
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public List<InventoryTransaction> getAllInventoryTransactions() {
        return inventoryTransactionRepository.findAll();
    }

    public InventoryTransaction getInventoryTransactionById(Long id) {
        return inventoryTransactionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Inventory Transaction not found with id: " + id
        ));
    }

    public List<InventoryTransaction> getInventoryTransactionsByType(TransactionType transactionType) {
        return inventoryTransactionRepository.findByTransactionType(transactionType);
    }

    public List<InventoryTransaction> getInventoryTransactionsByEmployeeId(Long employeeId) {
        return inventoryTransactionRepository.findByEmployee_Id(employeeId);
    }

    public List<InventoryTransaction> getInventoryTransactionsByInventoryItemId(Long inventoryItemId) {
        return inventoryTransactionRepository.findByInventoryItem_Id(inventoryItemId);
    }

    public List<InventoryTransaction> getInventoryTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        return inventoryTransactionRepository.findTransactionsWithinDateRange(startDate, endDate);
    }

    public InventoryTransaction createInventoryTransaction(InventoryTransaction inventoryTransaction) {
        if (inventoryTransactionRepository.findById(inventoryTransaction.getId()).isPresent()) {
            throw new RuntimeException("Inventory Transaction already exists");
        }

        Employee employee = employeeRepository.findById(inventoryTransaction.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryTransaction.getInventoryItem().getId())
                .orElseThrow(() -> new RuntimeException("Inventory Item not found"));

        inventoryTransaction.setInventoryItem(inventoryItem);
        inventoryTransaction.setEmployee(employee);
        return inventoryTransactionRepository.save(inventoryTransaction);
    }

    public void deleteInventoryTransaction(Long id) {
        inventoryTransactionRepository.deleteById(id);
    }

    public InventoryTransaction updateInventoryTransaction(Long id, InventoryTransaction updatedInventoryTransaction) {
        InventoryTransaction existingInventoryTransaction = inventoryTransactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Inventory Transaction not found with id: " + id
                ));

        existingInventoryTransaction.setInventoryItem(updatedInventoryTransaction.getInventoryItem());
        existingInventoryTransaction.setQuantity(updatedInventoryTransaction.getQuantity());
        existingInventoryTransaction.setTransactionType(updatedInventoryTransaction.getTransactionType());
        existingInventoryTransaction.setEmployee(updatedInventoryTransaction.getEmployee());
        existingInventoryTransaction.setNotes(updatedInventoryTransaction.getNotes());

        return inventoryTransactionRepository.save(existingInventoryTransaction);
    }

}
