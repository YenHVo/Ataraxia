package com.ataraxia.backend.service;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ataraxia.backend.entity.InventoryItem;
import com.ataraxia.backend.repository.InventoryItemRepository;
import java.util.List;
import com.ataraxia.backend.enums.InventoryCategory;

@Service
public class InventoryItemService {
    
    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
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
