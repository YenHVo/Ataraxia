package com.ataraxia.backend.controller;
import org.springframework.web.bind.annotation.*;
import com.ataraxia.backend.entity.InventoryTransaction;
import com.ataraxia.backend.service.InventoryTransactionService;
import java.util.List;
import com.ataraxia.backend.enums.TransactionType;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventory-transactions")
@CrossOrigin(origins = "http://localhost:5173")
public class InventoryTransactionController {
    
    private final InventoryTransactionService inventoryTransactionService;

    public InventoryTransactionController(InventoryTransactionService inventoryTransactionService) {
        this.inventoryTransactionService = inventoryTransactionService;
    }

    @GetMapping
    public List<InventoryTransaction> getAllInventoryTransactions() {
        return inventoryTransactionService.getAllInventoryTransactions();
    }

    @GetMapping("/{id}")
    public InventoryTransaction getInventoryTransactionById(@PathVariable Long id) {
        return inventoryTransactionService.getInventoryTransactionById(id);
    }

    @GetMapping("/type/{transactionType}")
    public List<InventoryTransaction> getInventoryTransactionsByType(@PathVariable TransactionType transactionType) {
        return inventoryTransactionService.getInventoryTransactionsByType(transactionType);
    }

    @GetMapping("/employee/{employeeId}")
    public List<InventoryTransaction> getInventoryTransactionsByEmployeeId(@PathVariable Long employeeId) {
        return inventoryTransactionService.getInventoryTransactionsByEmployeeId(employeeId);
    }

    @GetMapping("/inventory-item/{inventoryItemId}")
    public List<InventoryTransaction> getInventoryTransactionsByInventoryItemId(@PathVariable Long inventoryItemId) {
        return inventoryTransactionService.getInventoryTransactionsByInventoryItemId(inventoryItemId);
    }

    @GetMapping("/date-range")
    public List<InventoryTransaction> getInventoryTransactionsByDateRange(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        java.time.LocalDate start = java.time.LocalDate.parse(startDate);
        java.time.LocalDate end = java.time.LocalDate.parse(endDate);
        return inventoryTransactionService.getInventoryTransactionsByDateRange(start, end);
    }

    @PostMapping
    public InventoryTransaction createInventoryTransaction(@RequestBody @Valid InventoryTransaction inventoryTransaction) {
        return inventoryTransactionService.createInventoryTransaction(inventoryTransaction);
    }

    @DeleteMapping("/{id}")
    public void deleteInventoryTransaction(@PathVariable Long id) {
        inventoryTransactionService.deleteInventoryTransaction(id);
    }
}
