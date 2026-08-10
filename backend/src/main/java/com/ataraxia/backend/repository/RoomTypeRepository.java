package com.ataraxia.backend.repository;
import com.ataraxia.backend.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    Optional<RoomType> findByNameContainingIgnoreCase(String name);

    List<RoomType> findByCapacity(int capacity);

}
