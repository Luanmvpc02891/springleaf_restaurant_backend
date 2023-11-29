package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.RestaurantTable;
import com.springleaf_restaurant_backend.user.service.RestaurantTableService;

@RestController
public class RestaurantTableRestController {
    @Autowired
    private RestaurantTableService restauranTableService;


    @GetMapping("/public/restaurantTables")
    public List<RestaurantTable> getRestaurantTable() {
        return restauranTableService.getAllRestaurantTables();
    }

    @GetMapping("/public/restaurantTable/{tableId}")
    public RestaurantTable getTableById(@PathVariable("tableId") Long tableId) {
        return restauranTableService.getRestaurantTableById(tableId);
    }

    @PostMapping("/public/create/restaurantTable")
    public RestaurantTable createRestaurantTable(@RequestBody RestaurantTable restaurantTable) {
        return restauranTableService.saveRestaurantTable(restaurantTable);
    }

    @PutMapping("/public/update/restaurantTable")
    public RestaurantTable updateTable(@RequestBody RestaurantTable updatedTable) {
        return restauranTableService.saveRestaurantTable(updatedTable);
    }

    @DeleteMapping("/public/delete/restaurantTable/{tableId}")
    public void deleteInventory(@PathVariable("tableId") Long tableId) {
        restauranTableService.deleteRestaurantTable(tableId);
    }

}
