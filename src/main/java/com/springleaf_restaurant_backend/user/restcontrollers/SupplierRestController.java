package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.Supplier;
import com.springleaf_restaurant_backend.user.repositories.SupplierRepository;

@RestController
@RequestMapping("/api")
public class SupplierRestController {
    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/suppliers")
    public List<Supplier> getSupplier() {
        return supplierRepository.findAll();
    }

    @GetMapping("/supplier/{supplierId}")
    public Optional<Supplier> getSupplierById(@PathVariable("supplierId") Long supplierId) {
        return supplierRepository.findById(supplierId);
    }

    @PostMapping("/supplier")
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @PutMapping("/supplier/{supplierId}")
    public Supplier updateSupplier(@PathVariable("supplierId") Long supplierId,
            @RequestBody Supplier updatedSupplier) {
        Optional<Supplier> database = supplierRepository.findById(supplierId);
        if (database.isPresent()) {
            Supplier existing = database.get();
            existing.setName(updatedSupplier.getName());
            existing.setAddress(updatedSupplier.getAddress());
            existing.setEmail(updatedSupplier.getEmail());
            existing.setPhone(updatedSupplier.getPhone());
            return supplierRepository.save(existing);
        } else {
            return null;
        }
    }

    @DeleteMapping("/supplier/{supplierId}")
    public void deleteSupplier(@PathVariable("supplierId") Long supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
