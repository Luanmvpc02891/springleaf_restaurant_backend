package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurantById(Long id);

    Restaurant saveRestaurant(Restaurant restaurant);

    Restaurant updateRestaurant(Restaurant restaurant);

    void deleteRestaurant(Long id);
}
