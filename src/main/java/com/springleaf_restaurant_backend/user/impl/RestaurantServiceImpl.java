package com.springleaf_restaurant_backend.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.Ingredient;
import com.springleaf_restaurant_backend.user.entities.InventoryBranch;
import com.springleaf_restaurant_backend.user.entities.InventoryBranchIngredient;
import com.springleaf_restaurant_backend.user.entities.InventoryIngredient;
import com.springleaf_restaurant_backend.user.entities.OrderThreshold;
import com.springleaf_restaurant_backend.user.entities.Restaurant;
import com.springleaf_restaurant_backend.user.repositories.IngredientRepository;
import com.springleaf_restaurant_backend.user.repositories.InventoryBranchIngredientRepository;
import com.springleaf_restaurant_backend.user.repositories.InventoryBranchRepository;
import com.springleaf_restaurant_backend.user.repositories.InventoryIngredientRepository;
import com.springleaf_restaurant_backend.user.repositories.OrderThresholdRepository;
import com.springleaf_restaurant_backend.user.repositories.RestaurantRepository;
import com.springleaf_restaurant_backend.user.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final InventoryBranchRepository inventoryBranchRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderThresholdRepository orderThresholdRepository;
    private final IngredientRepository ingredientRepository;
    private final InventoryBranchIngredientRepository inventoryBranchIngredientRepository;
    private final InventoryIngredientRepository inventoryIngredientRepository;

    // Constructor injection
    public RestaurantServiceImpl(InventoryBranchRepository inventoryBranchRepository,
            OrderThresholdRepository orderThresholdRepository, IngredientRepository ingredientRepository,
            InventoryBranchIngredientRepository inventoryBranchIngredientRepository,InventoryIngredientRepository inventoryIngredientRepository,
            RestaurantRepository restaurantRepository) {
        this.inventoryBranchRepository = inventoryBranchRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderThresholdRepository = orderThresholdRepository;
        this.ingredientRepository = ingredientRepository;
        this.inventoryBranchIngredientRepository = inventoryBranchIngredientRepository;
        this.inventoryIngredientRepository = inventoryIngredientRepository;

    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant getRestaurantByInventoryBranchId(Long inventoryBranchId) {
        Optional<InventoryBranch> inventoryBranchOptional = inventoryBranchRepository.findById(inventoryBranchId);
        if (inventoryBranchOptional.isPresent()) {
            InventoryBranch inventoryBranch = inventoryBranchOptional.get();
            Long restaurantId = inventoryBranch.getRestaurantId();
            return restaurantRepository.findById(restaurantId).orElse(null);
        }
        return null;
    }

    @Override
    public InventoryBranch getInventoryBranchByRestaurantId(Long restaurantId) {
        return inventoryBranchRepository.findByRestaurantId(restaurantId);
    }

}
