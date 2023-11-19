package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAllSuppliers();

    Supplier getSupplierById(Long id);

    Supplier saveSupplier(Supplier supplier);

    void deleteSupplier(Long id);
}
