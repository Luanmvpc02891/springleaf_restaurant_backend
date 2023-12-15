package com.springleaf_restaurant_backend.user.service;

import java.util.List;

import com.springleaf_restaurant_backend.user.entities.InventoryBranchIngredient;

public interface InventoryBranchIngredientService {
  List<InventoryBranchIngredient> getAll();

  // List<Object[]> getInventoryInfo();
  InventoryBranchIngredient getInventoryBranchIngredientById(Long id);

  InventoryBranchIngredient saveInventoryBranchIngredient(InventoryBranchIngredient inventoryBranchIngredient);

  void deleteInventoryBranchIngredient(Long id);

}
