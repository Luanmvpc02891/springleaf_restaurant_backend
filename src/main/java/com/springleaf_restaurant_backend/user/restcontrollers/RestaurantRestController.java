package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.Restaurant;
import com.springleaf_restaurant_backend.user.repositories.RestaurantRepository;

@RestController
@RequestMapping("/api")
public class RestaurantRestController {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurant() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Optional<Restaurant> getRestaurantById(@PathVariable("restaurantId") Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }
}
