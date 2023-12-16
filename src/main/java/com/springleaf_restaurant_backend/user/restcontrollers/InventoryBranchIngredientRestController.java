package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.InventoryBranchIngredient;
import com.springleaf_restaurant_backend.user.service.InventoryBranchIngredientService;

@RestController
public class InventoryBranchIngredientRestController {
  @Autowired
  private InventoryBranchIngredientService inventoryBranchIngredientService;

  @GetMapping("/public/inventoryBranchIngredients")
  public List<InventoryBranchIngredient> getInventoryBranchIngredients() {
    return inventoryBranchIngredientService.getAll();
  }

  @PostMapping("/public/create/inventoryBranchIngredient")
  public InventoryBranchIngredient postMethodName(@RequestBody InventoryBranchIngredient inventoryBranchIngredient) {
    return inventoryBranchIngredientService.saveInventoryBranchIngredient(inventoryBranchIngredient);
  }

  @PutMapping("/public/update/inventoryBranchIngredient")
  public InventoryBranchIngredient updateInventory(
      @RequestBody InventoryBranchIngredient updatedInventoryBranchIngredient) {
    return inventoryBranchIngredientService.saveInventoryBranchIngredient(updatedInventoryBranchIngredient);
  }

  @DeleteMapping("/public/delete/inventoryBranchIngredient/{InventoryBranchIngredientId}")
  public void deleteInventoryBranchIngredient(
      @PathVariable("InventoryBranchIngredientId") Long InventoryBranchIngredientId) {
    inventoryBranchIngredientService.deleteInventoryBranchIngredient(InventoryBranchIngredientId);
  }
}
