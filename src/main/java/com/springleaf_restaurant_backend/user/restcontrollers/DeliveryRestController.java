package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Delivery;
import com.springleaf_restaurant_backend.user.service.DeliveryService;

@RestController
public class DeliveryRestController {
    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/public/deliveries")
    public List<Delivery> getDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/public/delivery/{deliveryId}")
    public Delivery getOneDelivery(@PathVariable("deliveryId") Long deliveryId){
        return deliveryService.getDeliveryById(deliveryId);
    }

    @PostMapping("/public/create/delivery")
    public Delivery createDelivery(@RequestBody Delivery delivery){
        return deliveryService.saveDelivery(delivery);
    }

    @PutMapping("/public/update/delivery")
    public Delivery updateDelivery(@RequestBody Delivery delivery){
        if(deliveryService.getDeliveryById(delivery.getDeliveryId()) != null){
            return deliveryService.saveDelivery(delivery);
        }else{
            return null;
        }
    }

    @DeleteMapping("/public/delete/delivery/{deliveryId}")
    public void deleteDelivery(@PathVariable("deliveryId") Long deliveryId){
        deliveryService.deleteDelivery(deliveryId);
    }
}
