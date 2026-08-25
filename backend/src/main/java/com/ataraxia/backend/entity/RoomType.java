package com.ataraxia.backend.entity;

import jakarta.persistence.*;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roomtypes")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;
    
    @Column
    @PositiveOrZero
    private BigDecimal basePrice;

    @Column (nullable = false)
    @PositiveOrZero
    private Integer capacity;

    @OneToMany(mappedBy = "roomType")
    @JsonIgnore
    private List<Room> rooms = new ArrayList<>();

    public RoomType() {
    }

    public RoomType(String name, String description, BigDecimal basePrice, Integer capacity) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.capacity = capacity;
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

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomType)) return false;
        RoomType that = (RoomType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", basePrice=" + basePrice +
                ", capacity=" + capacity +
                '}';
    }
}
