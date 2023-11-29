package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.GoodsReceipt;
import com.springleaf_restaurant_backend.user.service.GoodsReceiptService;

@RestController
public class GoodsReceiptRestController {
    @Autowired
    private GoodsReceiptService goodsReceiptService;

    @GetMapping("/public/goodsReceipts")
    public List<GoodsReceipt> getGoodsReceipt() {
        return goodsReceiptService.getAllDeliveries();
    }

    @GetMapping("/public/goodsReceipt/{GoodsReceiptId}")
    public GoodsReceipt getOneGoodsReceipt(@PathVariable("GoodsReceiptId") Long GoodsReceiptId){
        return goodsReceiptService.getGoodsReceiptById(GoodsReceiptId);
    }

    @PostMapping("/public/create/goodsReceipt")
    public GoodsReceipt createGoodsReceipt(@RequestBody GoodsReceipt GoodsReceipt){
        return goodsReceiptService.saveGoodsReceipt(GoodsReceipt);
    }

    @PutMapping("/public/update/goodsReceipt")
    public GoodsReceipt updateGoodsReceipt(@RequestBody GoodsReceipt GoodsReceipt){
        return goodsReceiptService.saveGoodsReceipt(GoodsReceipt);
    }

    @DeleteMapping("/public/delete/goodsReceipt/{GoodsReceiptId}")
    public void deleteGoodsReceipt(@PathVariable("GoodsReceiptId") Long GoodsReceiptId){
        goodsReceiptService.deleteGoodsReceipt(GoodsReceiptId);
    }
}
