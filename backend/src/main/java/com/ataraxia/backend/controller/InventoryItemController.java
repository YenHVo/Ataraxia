package com.ataraxia.backend.controller;
import org.springframework.web.bind.annotation.*;
import com.ataraxia.backend.entity.InventoryItem;
import com.ataraxia.backend.service.InventoryItemService;
import java.util.List;
import com.ataraxia.backend.enums.InventoryCategory;
import jakarta.validation.Valid;

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

    @GetMapping("/category/{category}")
    public List<InventoryItem> getInventoryItemsByCategory(@PathVariable InventoryCategory category) {
        return inventoryItemService.getInventoryItemsByCategory(category);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<InventoryItem> getInventoryItemsBySupplierId(@PathVariable Long supplierId) {
        return inventoryItemService.getInventoryItemsBySupplierId(supplierId);
    }

    @GetMapping("/low-stock")
    public List<InventoryItem> getInventoryItemsBelowReorderLevel() {
        return inventoryItemService.getItemsBelowReorderLevel();
    }

    @PostMapping
    public InventoryItem createInventoryItem(@RequestBody @Valid InventoryItem inventoryItem) {
        return inventoryItemService.createInventoryItem(inventoryItem);
    }

    @PutMapping("/{id}")
    public InventoryItem updateInventoryItem(@PathVariable Long id, @RequestBody @Valid InventoryItem updatedInventoryItem) {
        return inventoryItemService.updateInventoryItem(id, updatedInventoryItem);
    }

    @DeleteMapping("/{id}")
    public void deleteInventoryItem(@PathVariable Long id) {
        inventoryItemService.deleteInventoryItem(id);
    }
    
}
