package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.RestaurantTable;
import com.springleaf_restaurant_backend.user.repositories.RestauranTableRepository;

import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("/api")
public class RestaurantTableRestController {
    @Autowired
    private RestauranTableRepository restauranTableRepository;

     @Autowired
    private EntityManager entityManager;

    @GetMapping("/restaurantTables")
    public List<RestaurantTable> getRestaurantTable() {
        return restauranTableRepository.findAll();
    }

    @PostMapping("/restaurantTable")
    public RestaurantTable createRestaurantTable(@RequestBody RestaurantTable restaurantTable) {
        return restauranTableRepository.save(restaurantTable);
    }

    @PutMapping("/restaurantTable/{tableId}")
    public RestaurantTable updateTable(@PathVariable("tableId") Long tableId,
            @RequestBody RestaurantTable updatedTable) {
        Optional<RestaurantTable> databaseTable = restauranTableRepository.findById(tableId);
        if (databaseTable.isPresent()) {
            RestaurantTable existingTable = databaseTable.get();
            existingTable.setTableId(updatedTable.getTableId());
            existingTable.setTableName(updatedTable.getTableName());
            existingTable.setTableTypeId(updatedTable.getTableTypeId());
            existingTable.setTableStatusId(updatedTable.getTableStatusId());
            existingTable.setRestaurantId(updatedTable.getRestaurantId());
            return restauranTableRepository.save(existingTable);
        } else {
            return null;
        }
    }

    @DeleteMapping("/restaurantTable/{tableId}")
    public void deleteInventory(@PathVariable("tableId") Long tableId) {
        restauranTableRepository.deleteById(tableId);
    }

    @GetMapping("/restaurantTable/{tableId}")
    public Optional<RestaurantTable> getTableById(@PathVariable("tableId") Long tableId) {
        return restauranTableRepository.findById(tableId);
    }

    
    
}
