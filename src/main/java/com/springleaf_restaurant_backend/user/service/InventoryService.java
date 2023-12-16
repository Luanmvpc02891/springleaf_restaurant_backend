package com.springleaf_restaurant_backend.user.service;

import java.util.List;

import com.springleaf_restaurant_backend.user.entities.Inventory;
import com.springleaf_restaurant_backend.user.entities.InventoryBranchIngredient;
import com.springleaf_restaurant_backend.user.entities.InventoryIngredient;
import com.springleaf_restaurant_backend.user.entities.OrderThreshold;

public interface InventoryService {
    List<Inventory> getAllInventories();

    // List<Object[]> getInventoryInfo();
    Inventory getInventoryById(Long id);

    Inventory saveInventory(Inventory inventory);

    void deleteInventory(Long id);

    Long getTotalIngredientsInInventory();

    List<String> getIngredientsBelowThreshold(Long inventoryId);
    
     List<InventoryBranchIngredient> getIngredientsInBranch(Long inventoryBranchId);
}
