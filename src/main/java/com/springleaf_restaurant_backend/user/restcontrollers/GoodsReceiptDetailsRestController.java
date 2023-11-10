package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.GoodsReceiptDetails;
import com.springleaf_restaurant_backend.user.service.GoodsReceiptDetailsService;


@RestController
public class GoodsReceiptDetailsRestController {
    @Autowired
    GoodsReceiptDetailsService GoodsReceiptDetailsService;

    @GetMapping("/public/GoodsReceiptDetailss")
    public List<GoodsReceiptDetails> getGoodsReceiptDetailss() {
        return GoodsReceiptDetailsService.getAllGoodsReceiptDetailss();
    }

    @GetMapping("/public/GoodsReceiptDetails/{GoodsReceiptDetailsId}")
    public GoodsReceiptDetails getGoodsReceiptDetailsById(@PathVariable("GoodsReceiptDetailsId") Long GoodsReceiptDetailsId){
        return GoodsReceiptDetailsService.getGoodsReceiptDetailsById(GoodsReceiptDetailsId);
    }

    @PostMapping("/public/create/GoodsReceiptDetails")
    public GoodsReceiptDetails createDelivery(@RequestBody GoodsReceiptDetails GoodsReceiptDetails) {
        return GoodsReceiptDetailsService.saveGoodsReceiptDetails(GoodsReceiptDetails);
    }

    @PutMapping("/public/update/GoodsReceiptDetails")
    public GoodsReceiptDetails updateGoodsReceiptDetails(@RequestBody GoodsReceiptDetails GoodsReceiptDetails){
        GoodsReceiptDetails GoodsReceiptDetailsDatabase = GoodsReceiptDetailsService.getGoodsReceiptDetailsById(GoodsReceiptDetails.getGoodsReceiptDetailId());
        if(GoodsReceiptDetailsDatabase != null){
            return GoodsReceiptDetailsService.saveGoodsReceiptDetails(GoodsReceiptDetails);
        }else{
            return null;
        }
    }

    @DeleteMapping("/public/delete/GoodsReceiptDetails/{deliveryOrderid}")
    public void deleteDeliveryOrder(@PathVariable("deliveryOrderid") Long deliveryOrderid){
        GoodsReceiptDetailsService.deleteGoodsReceiptDetails(deliveryOrderid);
    }
    
}
