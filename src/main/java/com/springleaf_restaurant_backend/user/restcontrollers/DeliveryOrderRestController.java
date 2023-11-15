package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderService;

@RestController
public class DeliveryOrderRestController {
    @Autowired
    DeliveryOrderService deliveryOrderService;

    @GetMapping("/public/deliveryOrders")
    public List<DeliveryOrder> getDeliveryOrders() {
        return deliveryOrderService.getAllDeliveryOrders();
    }

    @GetMapping("/public/deliveryOrder/{deliveryOrderId}")
    public DeliveryOrder getOneDeliveryOrder(@PathVariable("deliveryOrderId") Long deliveryOrderId){
        return deliveryOrderService.getDeliveryOrderById(deliveryOrderId);
    }

    @PostMapping("/public/create/deliveryOrder")
    public DeliveryOrder createDeliveryOrder(@RequestBody DeliveryOrder deliveryOrder){
        return deliveryOrderService.saveDeliveryOrder(deliveryOrder);
    }

    @PutMapping("/public/update/deliveryOrder")
    public DeliveryOrder updateDeliveryOrder(@RequestBody DeliveryOrder deliveryOrder){
        return deliveryOrderService.saveDeliveryOrder(deliveryOrder);
    }
    
    @DeleteMapping("/public/delete/deliveryOrder/{deliveryOrderId}")
    public void deleteDeliveryOrder(@PathVariable("deliveryOrderId") Long deliveryOrderId){
        deliveryOrderService.deleteDeliveryOrder(deliveryOrderId);
    }
}
