package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Supplier;
import com.springleaf_restaurant_backend.user.service.SupplierService;

@RestController
public class SupplierRestController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/public/suppliers")
    public List<Supplier> getSupplier() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/public/supplier/{supplierId}")
    public Supplier getSupplierById(@PathVariable("supplierId") Long supplierId) {
        return supplierService.getSupplierById(supplierId);
    }

    @PostMapping("/public/create/supplier")
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.saveSupplier(supplier);
    }

    @PutMapping("/public/update/supplier/{supplierId}")
    public Supplier updateSupplier(@RequestBody Supplier updatedSupplier) {
        return supplierService.saveSupplier(updatedSupplier);
    }

    @DeleteMapping("/public/delete/supplier/{supplierId}")
    public void deleteSupplier(@PathVariable("supplierId") Long supplierId) {
        supplierService.deleteSupplier(supplierId);
    }
}
