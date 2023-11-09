package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Restaurant;
import com.springleaf_restaurant_backend.user.service.RestaurantService;

@RestController
public class RestaurantRestController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/public/restaurants")
    public List<Restaurant> getRestaurant() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/public/restaurant/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable("restaurantId") Long restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @PostMapping("/public/create/restaurant")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant){
        return restaurantService.saveRestaurant(restaurant);
    }

    @PostMapping("/public/update/restaurant")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant){
        if(restaurantService.getRestaurantById(restaurant.getRestaurantId()) != null){
            return restaurantService.saveRestaurant(restaurant);
        }else{
            return null;
        }
    }

    @DeleteMapping("/public/delete/restaurant/{restaurantId}")
    public void deleteRestaurantById(@PathVariable("restaurantId") Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }
}
