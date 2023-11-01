package com.springleaf_restaurant_backend.user.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springleaf_restaurant_backend.user.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT i.inventoryId, ing.name as ingredientName, sup.name as supplierName " +
            "FROM Inventory i " +
            "JOIN Ingredient ing ON i.ingredientId = ing.ingredientId " +
            "JOIN Supplier sup ON i.supplierId = sup.supplierId")
    List<Object[]> getInventoryInfo();
}
