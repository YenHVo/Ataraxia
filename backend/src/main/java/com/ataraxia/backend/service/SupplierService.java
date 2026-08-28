package com.ataraxia.backend.service;
import com.ataraxia.backend.entity.Supplier;
import com.ataraxia.backend.repository.SupplierRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Supplier not found with id: " + id
        ));
    }

    public Supplier getSupplierByEmail(String email) {
        return supplierRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Supplier not found with email: " + email
        ));
    }

    public List<Supplier> getSupplierByName(String name) {
        return supplierRepository.findByNameContainingIgnoreCase(name);
    }

    public Supplier createSupplier(Supplier supplier) {
        if (supplierRepository.findByEmail(supplier.getEmail()).isPresent()) {
            throw new RuntimeException("Supplier already exists");
        }
        
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        Supplier existingSupplier = supplierRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Supplier not found with id: " + id
            ));

        if (!existingSupplier.getEmail().equals(updatedSupplier.getEmail())) {
            if (supplierRepository.findByEmail(updatedSupplier.getEmail()).isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Email is already in use: " + updatedSupplier.getEmail()
                );
            }
        }

        existingSupplier.setName(updatedSupplier.getName());
        existingSupplier.setEmail(updatedSupplier.getEmail());
        existingSupplier.setPhone(updatedSupplier.getPhone());
        existingSupplier.setAddress(updatedSupplier.getAddress());

        return supplierRepository.save(existingSupplier);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
    
}
