package com.ataraxia.backend.entity;
import jakarta.persistence.*;
import com.ataraxia.backend.enums.InventoryCategory;
import com.ataraxia.backend.enums.InventoryUnit;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "inventory_items")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InventoryCategory category;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal unitCost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InventoryUnit unit;

    @Column(nullable = false)
    private int reorderLevel;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    public InventoryItem() {
    }

    public InventoryItem(String name, String description, InventoryCategory category, int quantity, BigDecimal unitCost, InventoryUnit unit, int reorderLevel, Supplier supplier) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.unit = unit;
        this.reorderLevel = reorderLevel;
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InventoryCategory getCategory() {
        return category;
    }

    public void setCategory(InventoryCategory category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public InventoryUnit getUnit() {
        return unit;
    }

    public void setUnit(InventoryUnit unit) {
        this.unit = unit;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryItem)) return false;
        InventoryItem that = (InventoryItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", quantity=" + quantity +
                ", unitCost=" + unitCost +
                ", unit=" + unit +
                ", reorderLevel=" + reorderLevel +
                ", supplier=" + supplier +
                '}';
    }

}
