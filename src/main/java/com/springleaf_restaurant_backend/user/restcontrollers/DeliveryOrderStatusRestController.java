package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrderStatus;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderStatusService;

@RestController
public class DeliveryOrderStatusRestController {
    @Autowired
    DeliveryOrderStatusService deliveryOrderStatusService;

    @GetMapping("/public/deliveryOrderStatuses")
    public List<DeliveryOrderStatus> getdeliveryOrderStatuses() {
        return deliveryOrderStatusService.getAllDeliveryOrderStatuses();
    }

    @GetMapping("/public/deliveryOrderStatus/{deliveryorderStatusId}")
    public DeliveryOrderStatus getOneDeliveryOrderStatus(@PathVariable("deliveryorderStatusId") Long deliveryorderStatusId){
        return deliveryOrderStatusService.getDeliveryOrderStatusById(deliveryorderStatusId);
    }

    @PostMapping("/public/create/deliveryOrderStatus")
    public DeliveryOrderStatus createDeliveryOrderStatus(@RequestBody DeliveryOrderStatus deliveryOrderStatus){
        // if(deliveryOrderStatusService.findByDeliveryOrderStatusName(deliveryOrderStatus.getDeliveryOrderStatusName()) == null){
        //      return deliveryOrderStatusService.saveDeliveryOrderStatus(deliveryOrderStatus);
        // }else{
            return null;
        //}
       
    }

    @PutMapping("/public/update/deliveryOrderStatus")
    public DeliveryOrderStatus updateDeliveryOrderStatus(@RequestBody DeliveryOrderStatus deliveryOrderStatus){
        if(deliveryOrderStatusService.getDeliveryOrderStatusById(deliveryOrderStatus.getDeliveryOrderStatusId()) != null){
            return deliveryOrderStatusService.saveDeliveryOrderStatus(deliveryOrderStatus);
        }else{
            return null;
        }
    }

    @DeleteMapping("/public/delete/deliveryOrderStatus/{deliveryOrderStatusId}")
    public void deleteDeliveryOrderStatus(@PathVariable("deliveryOrderStatusId") Long deliveryOrderStatusId){
        deliveryOrderStatusService.deleteDeliveryOrderStatus(deliveryOrderStatusId);
    }
}
