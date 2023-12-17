package com.springleaf_restaurant_backend.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.Ingredient;
import com.springleaf_restaurant_backend.user.entities.InventoryBranchIngredient;
import com.springleaf_restaurant_backend.user.entities.OrderThreshold;
import com.springleaf_restaurant_backend.user.repositories.IngredientRepository;
import com.springleaf_restaurant_backend.user.repositories.InventoryBranchIngredientRepository;
import com.springleaf_restaurant_backend.user.repositories.OrderThresholdRepository;
import com.springleaf_restaurant_backend.user.service.OrderThresholdService;

@Service
public class OrderThresholdServiceImpl implements OrderThresholdService {
    private final OrderThresholdRepository orderThresholdRepository;
    private final InventoryBranchIngredientRepository inventoryBranchIngredientRepository;
    private final IngredientRepository ingredientRepository;

    public OrderThresholdServiceImpl(OrderThresholdRepository orderThresholdRepository,
            InventoryBranchIngredientRepository inventoryBranchIngredientRepository,
            IngredientRepository ingredientRepository) {
        this.orderThresholdRepository = orderThresholdRepository;
        this.inventoryBranchIngredientRepository = inventoryBranchIngredientRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<OrderThreshold> getAllOrderThresholds() {
        return orderThresholdRepository.findAll();
    }

    @Override
    public OrderThreshold getOrderThresholdById(Long id) {
        return orderThresholdRepository.findById(id).orElse(null);
    }

    @Override
    public OrderThreshold saveOrderThreshold(OrderThreshold orderThreshold) {
        return orderThresholdRepository.save(orderThreshold);
    }

    @Override
    public void deleteOrderThreshold(Long id) {
        orderThresholdRepository.deleteById(id);
    }

    // @Override
    // public List<String> checkThresholdForRestaurant(Long restaurantId) {
    // List<String> ingredientsBelowThreshold = new ArrayList<>();
    // List<InventoryBranchIngredient> ingredientsInBranch =
    // inventoryBranchIngredientRepository.findByInventoryBranchId(restaurantId);

    // for (InventoryBranchIngredient ingredient : ingredientsInBranch) {
    // Long ingredientId = ingredient.getIngredientId();
    // Double quantity = ingredient.getQuantity();

    // OrderThreshold threshold =
    // orderThresholdRepository.findByIngredientIdAndInventoryBranchId(ingredientId,
    // restaurantId);
    // if (threshold != null && quantity < threshold.getReorderPoint()) {
    // // Lấy tên nguyên liệu từ Repository để hiển thị đầy đủ
    // Ingredient ingredientDetails =
    // ingredientRepository.findById(ingredientId).orElse(null);
    // if (ingredientDetails != null) {
    // ingredientsBelowThreshold.add("Nguyên liệu thiếu ngưỡng: " +
    // ingredientDetails.getName());
    // }
    // }
    // }
    // return ingredientsBelowThreshold;
    // }

    @Override
    public List<String> checkThresholdForRestaurant(Long restaurantId) {
        List<String> ingredientsBelowThreshold = new ArrayList<>();
        List<InventoryBranchIngredient> ingredientsInBranch = inventoryBranchIngredientRepository
                .findByInventoryBranchId(restaurantId);

        for (InventoryBranchIngredient ingredient : ingredientsInBranch) {
            Long ingredientId = ingredient.getIngredientId();
            Double quantity = ingredient.getQuantity();

            OrderThreshold threshold = orderThresholdRepository.findByIngredientIdAndInventoryBranchId(ingredientId,
                    restaurantId);
            if (threshold != null && quantity < threshold.getReorderPoint()) {
                Ingredient ingredientDetails = ingredientRepository.findById(ingredientId).orElse(null);
                if (ingredientDetails != null) {
                    ingredientsBelowThreshold.add("Nguyên liệu thiếu ngưỡng: " + ingredientDetails.getName());
                }
            }
        }
        return ingredientsBelowThreshold;
    }

    @Override
    public List<String> checkThresholdForInventoryBranch(Long inventoryBranchId) {
        List<String> ingredientsBelowThreshold = new ArrayList<>();
        List<InventoryBranchIngredient> ingredientsInBranch = inventoryBranchIngredientRepository
                .findByInventoryBranchId(inventoryBranchId);

        for (InventoryBranchIngredient ingredient : ingredientsInBranch) {
            Long ingredientId = ingredient.getIngredientId();
            Double quantity = ingredient.getQuantity();

            OrderThreshold threshold = orderThresholdRepository.findByIngredientIdAndInventoryBranchId(ingredientId,
                    inventoryBranchId);
            if (threshold != null && quantity < threshold.getReorderPoint()) {
                Ingredient ingredientDetails = ingredientRepository.findById(ingredientId).orElse(null);
                if (ingredientDetails != null) {
                    ingredientsBelowThreshold.add("Nguyên liệu thiếu ngưỡng: " + ingredientDetails.getName());
                }
            }
        }
        return ingredientsBelowThreshold;
    }
}
