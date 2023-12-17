package com.springleaf_restaurant_backend.user.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springleaf_restaurant_backend.user.entities.InventoryBranchIngredient;

public interface InventoryBranchIngredientRepository extends JpaRepository<InventoryBranchIngredient, Long> {
  List<InventoryBranchIngredient> findByInventoryId(Long inventoryId);

  List<InventoryBranchIngredient> findByInventoryBranchId(Long inventoryBranchId);
  Optional<InventoryBranchIngredient> findByInventoryBranchIdAndIngredientId(Long inventoryBranchId, Long ingredientId);

}
