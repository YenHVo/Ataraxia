package com.ataraxia.backend.entity;
import com.ataraxia.backend.enums.RoomStatus;
import jakarta.persistence.*;
import java.util.Objects;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roomNumber;

    @Column(nullable = false)
    private int floor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus status;

    @ManyToOne
    @JoinColumn(name = "roomtype_id")
    private RoomType roomType;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

    public Room() {
    }

    public Room(String roomNumber, Integer floor, RoomStatus status, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.status = status;
        this.roomType = roomType;
    }

    public Long getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
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
