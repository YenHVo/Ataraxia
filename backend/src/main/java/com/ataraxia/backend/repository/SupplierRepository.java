package com.ataraxia.backend.repository;
import com.ataraxia.backend.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByEmail(String email);

    List<Supplier> findByNameContainingIgnoreCase(String name);
}
