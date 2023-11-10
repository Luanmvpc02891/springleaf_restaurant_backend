package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.GoodsReceipt;
import com.springleaf_restaurant_backend.user.service.GoodsReceiptService;

@RestController
public class GoodsReceiptRestController {
    @Autowired
    private GoodsReceiptService GoodsReceiptService;

    @GetMapping("/public/deliveries")
    public List<GoodsReceipt> getGoodsReceipt() {
        return GoodsReceiptService.getAllDeliveries();
    }

    @GetMapping("/public/GoodsReceipt/{GoodsReceiptId}")
    public GoodsReceipt getOneGoodsReceipt(@PathVariable("GoodsReceiptId") Long GoodsReceiptId){
        return GoodsReceiptService.getGoodsReceiptById(GoodsReceiptId);
    }

    @PostMapping("/public/create/GoodsReceipt")
    public GoodsReceipt createGoodsReceipt(@RequestBody GoodsReceipt GoodsReceipt){
        return GoodsReceiptService.saveGoodsReceipt(GoodsReceipt);
    }

    @PutMapping("/public/update/GoodsReceipt")
    public GoodsReceipt updateGoodsReceipt(@RequestBody GoodsReceipt GoodsReceipt){
        if(GoodsReceiptService.getGoodsReceiptById(GoodsReceipt.getGoodsReceiptId()) != null){
            return GoodsReceiptService.saveGoodsReceipt(GoodsReceipt);
        }else{
            return null;
        }
    }

    @DeleteMapping("/public/delete/GoodsReceipt/{GoodsReceiptId}")
    public void deleteGoodsReceipt(@PathVariable("GoodsReceiptId") Long GoodsReceiptId){
        GoodsReceiptService.deleteGoodsReceipt(GoodsReceiptId);
    }
}
