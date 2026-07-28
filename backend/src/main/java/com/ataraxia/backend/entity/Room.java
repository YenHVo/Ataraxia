package com.ataraxia.backend.entity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer roomNumber;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "roomtype_id")
    private RoomType roomType;

    public Room() {
    }

    public Room(Integer roomNumber, Integer floor, String status, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.status = status;
        this.roomType = roomType;
    }

    public Long getId() {
        return id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", floor=" + floor +
                ", status='" + status + '\'' +
                ", roomType=" + roomType +
                '}';
    }

}
