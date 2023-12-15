package com.springleaf_restaurant_backend.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.Ingredient;
import com.springleaf_restaurant_backend.user.entities.Inventory;
import com.springleaf_restaurant_backend.user.entities.InventoryIngredient;
import com.springleaf_restaurant_backend.user.entities.OrderThreshold;
import com.springleaf_restaurant_backend.user.repositories.IngredientRepository;
import com.springleaf_restaurant_backend.user.repositories.InventoryBranchRepository;
import com.springleaf_restaurant_backend.user.repositories.InventoryIngredientRepository;
import com.springleaf_restaurant_backend.user.repositories.InventoryRepository;
import com.springleaf_restaurant_backend.user.repositories.OrderThresholdRepository;
import com.springleaf_restaurant_backend.user.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryBranchRepository inventoryBranchRepository;
    private final OrderThresholdRepository orderThresholdRepository;
    private final InventoryIngredientRepository inventoryIngredientRepository;
    private final IngredientRepository ingredientRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository,
            InventoryBranchRepository inventoryBranchRepository,
            OrderThresholdRepository orderThresholdRepository, IngredientRepository ingredientRepository,
            InventoryIngredientRepository inventoryIngredientRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryBranchRepository = inventoryBranchRepository;
        this.orderThresholdRepository = orderThresholdRepository;
        this.inventoryIngredientRepository = inventoryIngredientRepository;
        this.ingredientRepository = ingredientRepository;

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

    @Override
    public List<String> getIngredientsBelowThreshold(Long inventoryId) {
        // Lấy danh sách nguyên liệu có số lượng dưới ngưỡng đặt hàng
        List<String> ingredientsBelowThreshold = new ArrayList<>();

        // Lấy số lượng nguyên liệu của kho dựa vào inventoryId
        List<InventoryIngredient> inventoryIngredients = inventoryIngredientRepository.findByInventoryId(inventoryId);

        // Lấy ngưỡng đặt hàng từ bảng OrderThreshold dựa trên inventoryId
        List<OrderThreshold> orderThresholds = orderThresholdRepository.findByInventoryId(inventoryId);

        // Lấy thông tin nguyên liệu dựa trên InventoryIngredient và OrderThreshold
        for (InventoryIngredient inventoryIngredient : inventoryIngredients) {
            for (OrderThreshold orderThreshold : orderThresholds) {
                if (inventoryIngredient.getIngredientId().equals(orderThreshold.getIngredientId())
                        && inventoryIngredient.getQuantity() <= orderThreshold.getReorderPoint()) {
                    // Lấy thông tin nguyên liệu từ Repository dựa trên IngredientId
                    Optional<Ingredient> optionalIngredient = ingredientRepository
                            .findById(inventoryIngredient.getIngredientId());
                    if (optionalIngredient.isPresent()) {
                        Ingredient ingredient = optionalIngredient.get();
                        ingredientsBelowThreshold.add(ingredient.getName());
                    }
                }
            }
        }

        return ingredientsBelowThreshold;
    }



}
