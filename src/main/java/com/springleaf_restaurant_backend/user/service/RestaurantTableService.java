package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.RestaurantTable;

import java.util.List;

public interface RestaurantTableService {
    List<RestaurantTable> getAllRestaurantTables();

    RestaurantTable getRestaurantTableById(Long id);

    RestaurantTable saveRestaurantTable(RestaurantTable restaurantTable);

    RestaurantTable updateRestaurantTable(RestaurantTable restaurantTable);

    void deleteRestaurantTable(Long id);
}
