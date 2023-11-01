package com.springleaf_restaurant_backend.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springleaf_restaurant_backend.user.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
