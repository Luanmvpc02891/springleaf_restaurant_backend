package com.springleaf_restaurant_backend.user.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.InventoryBranchIngredient;
import com.springleaf_restaurant_backend.user.repositories.InventoryBranchIngredientRepository;
import com.springleaf_restaurant_backend.user.service.InventoryBranchIngredientService;

@Service
public class InventoryBranchIngredientServiceImpl implements InventoryBranchIngredientService {
  private final InventoryBranchIngredientRepository inventoryBranchIngredientRepository;

  public InventoryBranchIngredientServiceImpl(InventoryBranchIngredientRepository inventoryBranchIngredientRepository) {
    this.inventoryBranchIngredientRepository = inventoryBranchIngredientRepository;
  }

  @Override
  public InventoryBranchIngredient getInventoryBranchIngredientById(Long id) {
    return inventoryBranchIngredientRepository.findById(id).orElse(null);
  }

  @Override
  public List<InventoryBranchIngredient> getAll() {
    return inventoryBranchIngredientRepository.findAll();
  }

  @Override
  public InventoryBranchIngredient saveInventoryBranchIngredient(InventoryBranchIngredient inventoryBranchIngredient) {
    return inventoryBranchIngredientRepository.save(inventoryBranchIngredient);
  }

  @Override
  public void deleteInventoryBranchIngredient(Long id) {
    inventoryBranchIngredientRepository.deleteById(id);
  }
}
