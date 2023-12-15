package com.springleaf_restaurant_backend.user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springleaf_restaurant_backend.user.entities.InventoryBranchIngredient;

public interface InventoryBranchIngredientRepository extends JpaRepository<InventoryBranchIngredient, Long> {
  List<InventoryBranchIngredient> findByInventoryId(Long inventoryId);
  InventoryBranchIngredient findByInventoryIdAndIngredientId(Long inventoryId, Long ingredientId);
}
