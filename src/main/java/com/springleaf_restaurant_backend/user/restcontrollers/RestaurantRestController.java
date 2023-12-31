package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/public/update/restaurant")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant){
        return restaurantService.saveRestaurant(restaurant);
    }

    @DeleteMapping("/public/delete/restaurant/{restaurantId}")
    public void deleteRestaurantById(@PathVariable("restaurantId") Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }

    @GetMapping("/public/count")
    public ResponseEntity<Long> countRestaurants() {
        long count = restaurantService.countRestaurants();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
