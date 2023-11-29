package com.springleaf_restaurant_backend.user.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.Inventory;
import com.springleaf_restaurant_backend.user.entities.OrderThreshold;
import com.springleaf_restaurant_backend.user.repositories.InventoryBranchRepository;
import com.springleaf_restaurant_backend.user.repositories.InventoryRepository;
import com.springleaf_restaurant_backend.user.repositories.OrderThresholdRepository;
import com.springleaf_restaurant_backend.user.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryBranchRepository inventoryBranchRepository;
   

    public InventoryServiceImpl(InventoryRepository inventoryRepository,
            InventoryBranchRepository inventoryBranchRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryBranchRepository = inventoryBranchRepository;
      
    }

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    @Override
    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public Long getTotalIngredientsInInventory() {
        Long totalIngredientsInInventory = inventoryRepository.count();
        Long totalIngredientsInInventoryBranch = inventoryBranchRepository.count();
        return totalIngredientsInInventory + totalIngredientsInInventoryBranch;
    }


}
