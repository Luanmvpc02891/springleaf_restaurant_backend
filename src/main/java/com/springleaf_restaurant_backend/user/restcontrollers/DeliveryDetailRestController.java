package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.DeliveryDetail;
import com.springleaf_restaurant_backend.user.service.DeliveryDetailService;


@RestController
public class DeliveryDetailRestController {
    @Autowired
    DeliveryDetailService deliveryDetailService;

    @GetMapping("/public/deliveryDetails")
    public List<DeliveryDetail> getdeliveryDetails() {
        return deliveryDetailService.getAllDeliveryDetails();
    }

    @GetMapping("/public/deliveryDetail/{deliveryDetailId}")
    public DeliveryDetail getDeliveryDetailById(@PathVariable("deliveryDetailId") Long deliveryDetailId){
        return deliveryDetailService.getDeliveryDetailById(deliveryDetailId);
    }

    @PostMapping("/public/create/deliveryDetail")
    public DeliveryDetail createDelivery(@RequestBody DeliveryDetail deliveryDetail) {
        return deliveryDetailService.saveDeliveryDetail(deliveryDetail);
    }

    @PutMapping("/public/update/deliveryDetail")
    public DeliveryDetail updateDeliveryDetail(@RequestBody DeliveryDetail deliveryDetail){
        DeliveryDetail deliveryDetailDatabase = deliveryDetailService.getDeliveryDetailById(deliveryDetail.getDeliveryDetailId());
        if(deliveryDetailDatabase != null){
            return deliveryDetailService.saveDeliveryDetail(deliveryDetail);
        }else{
            return null;
        }
    }

    @DeleteMapping("/public/delete/deliveryDetail/{deliveryOrderid}")
    public void deleteDeliveryOrder(@PathVariable("deliveryOrderid") Long deliveryOrderid){
        deliveryDetailService.deleteDeliveryDetail(deliveryOrderid);
    }
    
}
