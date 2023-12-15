package com.springleaf_restaurant_backend.user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springleaf_restaurant_backend.user.entities.InventoryIngredient;

public interface InventoryIngredientRepository extends JpaRepository<InventoryIngredient, Long> {
  List<InventoryIngredient> findByInventoryId(Long inventoryId);

  InventoryIngredient findByInventoryIdAndIngredientId(Long inventoryId, Long ingredientId);

 
}
