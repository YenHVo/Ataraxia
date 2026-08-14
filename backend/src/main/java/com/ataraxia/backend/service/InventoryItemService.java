package com.ataraxia.backend.service;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ataraxia.backend.entity.InventoryItem;
import com.ataraxia.backend.repository.InventoryItemRepository;
import com.ataraxia.backend.repository.SupplierRepository;
import com.ataraxia.backend.entity.Supplier;
import java.util.List;
import com.ataraxia.backend.enums.InventoryCategory;

@Service
public class InventoryItemService {
    
    private final InventoryItemRepository inventoryItemRepository;

    private final SupplierRepository supplierRepository;

    public InventoryItemService(InventoryItemRepository inventoryItemRepository, SupplierRepository supplierRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }

    public InventoryItem getInventoryItemById(Long id) {
        return inventoryItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Inventory item not found with id: " + id
        ));
    }

    public List<InventoryItem> getInventoryItemsByCategory(InventoryCategory category) {
        return inventoryItemRepository.findByCategory(category);
    }

    public List<InventoryItem> getInventoryItemsBySupplierId(Long supplierId) {
        return inventoryItemRepository.findBySupplier_Id(supplierId);
    }

    public List<InventoryItem> getItemsBelowReorderLevel() {
        return inventoryItemRepository.findItemsBelowReorderLevel();
    }

    public InventoryItem createInventoryItem(InventoryItem inventoryItem) {
        Supplier supplier = supplierRepository.findById(inventoryItem.getSupplier().getId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        inventoryItem.setSupplier(supplier);
        return inventoryItemRepository.save(inventoryItem);
    }

    public InventoryItem updateInventoryItem(Long id, InventoryItem updatedInventoryItem) {
        InventoryItem existingInventoryItem = inventoryItemRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Inventory item not found with id: " + id
            ));

        existingInventoryItem.setName(updatedInventoryItem.getName());
        existingInventoryItem.setDescription(updatedInventoryItem.getDescription());
        existingInventoryItem.setCategory(updatedInventoryItem.getCategory());
        existingInventoryItem.setQuantity(updatedInventoryItem.getQuantity());
        existingInventoryItem.setUnitCost(updatedInventoryItem.getUnitCost());
        existingInventoryItem.setUnit(updatedInventoryItem.getUnit());
        existingInventoryItem.setReorderLevel(updatedInventoryItem.getReorderLevel());
        existingInventoryItem.setSupplier(updatedInventoryItem.getSupplier());

        return inventoryItemRepository.save(existingInventoryItem);
    }

    public void deleteInventoryItem(Long id) {
        inventoryItemRepository.deleteById(id);
    }
}
