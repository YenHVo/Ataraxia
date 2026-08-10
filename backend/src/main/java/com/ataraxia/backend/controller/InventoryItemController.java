package com.ataraxia.backend.controller;
import org.springframework.web.bind.annotation.*;
import com.ataraxia.backend.entity.InventoryItem;
import com.ataraxia.backend.service.InventoryItemService;
import java.util.List;  

@RestController
@RequestMapping("/api/inventory-items")
@CrossOrigin(origins = "http://localhost:5173")
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;

    public InventoryItemController(InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    @GetMapping
    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemService.getAllInventoryItems();
    }

    @GetMapping("/{id}")
    public InventoryItem getInventoryItemById(@PathVariable Long id) {
        return inventoryItemService.getInventoryItemById(id);
    }

    @PostMapping
    public InventoryItem createInventoryItem(@RequestBody InventoryItem inventoryItem) {
        return inventoryItemService.createInventoryItem(inventoryItem);
    }

    @PutMapping("/{id}")
    public InventoryItem updateInventoryItem(@PathVariable Long id, @RequestBody InventoryItem updatedInventoryItem) {
        return inventoryItemService.updateInventoryItem(id, updatedInventoryItem);
    }

    @DeleteMapping("/{id}")
    public void deleteInventoryItem(@PathVariable Long id) {
        inventoryItemService.deleteInventoryItem(id);
    }
    
}
