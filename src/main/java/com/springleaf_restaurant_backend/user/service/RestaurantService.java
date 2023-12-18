package com.springleaf_restaurant_backend.user.service;

import java.util.List;

import com.springleaf_restaurant_backend.user.entities.InventoryBranch;
import com.springleaf_restaurant_backend.user.entities.Restaurant;

public interface RestaurantService {
  List<Restaurant> getAllRestaurants();

  Restaurant getRestaurantById(Long id);

  Restaurant saveRestaurant(Restaurant restaurant);

  Restaurant updateRestaurant(Restaurant restaurant);

  void deleteRestaurant(Long id);

  Restaurant getRestaurantByInventoryBranchId(Long inventoryBranchId);

  InventoryBranch getInventoryBranchByRestaurantId(Long restaurantId);

  long countRestaurants();
}
