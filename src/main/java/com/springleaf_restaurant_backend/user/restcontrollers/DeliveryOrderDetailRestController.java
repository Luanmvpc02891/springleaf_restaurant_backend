package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrderDetail;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderDetailService;

@RestController
public class DeliveryOrderDetailRestController {
    @Autowired
    DeliveryOrderDetailService deliveryOrderDetailService;

    @GetMapping("/public/deliveryOrderDetails")
    public List<DeliveryOrderDetail> getDeliveryOrderDetails() {
        return deliveryOrderDetailService.getAllDeliveryOrderDetails();
    }

    @GetMapping("/public/deliveryOrderDetail/{deliveryOrderDetailId}")
    public DeliveryOrderDetail getOneDeliveryDetail(@PathVariable("deliveryOrderDetailId") Long deliveryOrderDetailId){
        return deliveryOrderDetailService.getDeliveryOrderDetailById(deliveryOrderDetailId);
    }

    @PostMapping("/public/create/deliveryOrderDetail")
    public DeliveryOrderDetail createDeliveryOrderDetail(@RequestBody DeliveryOrderDetail deliveryOrderDetail){
        return deliveryOrderDetailService.saveDeliveryOrderDetail(deliveryOrderDetail);
    }

    @PutMapping("/public/update/deliveryOrderDetail")
    public DeliveryOrderDetail updateDeliveryOrderDetail(@RequestBody DeliveryOrderDetail deliveryOrderDetail){
        if(deliveryOrderDetailService.getDeliveryOrderDetailById(deliveryOrderDetail.getDeliveryOrderDetailId()) != null){
            return deliveryOrderDetailService.saveDeliveryOrderDetail(deliveryOrderDetail);
        }else{
            return null;
        }
    }

    @DeleteMapping("/public/delete/deliveryOrderDetail/{deliveryOrderDetailId}")
    public void deleteDeliveryOrderDetail(@PathVariable("deliveryOrderDetailId") Long deliveryOrderDetailId){
        deliveryOrderDetailService.deleteDeliveryOrderDetail(deliveryOrderDetailId);
    }

}
