package com.ataraxia.backend.controller;
import com.ataraxia.backend.entity.Supplier;
import com.ataraxia.backend.service.SupplierService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "http://localhost:5173")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @GetMapping("/name/{name}")
    public List<Supplier> getSupplierByName(@PathVariable String name) {
        return supplierService.getSupplierByName(name);
    }

    @GetMapping("/email/{email}")
    public Supplier getSupplierByEmail(@PathVariable String email) {
        return supplierService.getSupplierByEmail(email);
    }

    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        return supplierService.updateSupplier(id, supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
    }
    
}
