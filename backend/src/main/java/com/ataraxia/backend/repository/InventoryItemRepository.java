package com.ataraxia.backend.repository;
import com.ataraxia.backend.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.ataraxia.backend.enums.InventoryCategory;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    List<InventoryItem> findByCategory(InventoryCategory category);

    List<InventoryItem> findBySupplier_Id(Long supplierId);

    @Query("SELECT i FROM InventoryItem i WHERE i.quantity <= i.reorderLevel")
    List<InventoryItem> findItemsBelowReorderLevel();
    
}
