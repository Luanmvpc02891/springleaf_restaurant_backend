package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.service.DiscountService;
import com.springleaf_restaurant_backend.user.entities.Discount;

@RestController
public class DiscountRestController {
    @Autowired
    DiscountService discountService;

    @GetMapping("/public/discounts")
    public List<Discount> getDiscounts(){
        return discountService.getAllDiscounts();
    }

    @GetMapping("/public/discount/{eventId}")
    public Discount getDiscount(@PathVariable("eventId") Integer eventId){
        return discountService.getDiscountById(eventId);
    }

    @PostMapping("/public/create/discount")
    public Discount createDiscount(@RequestBody Discount discount){
        return discountService.saveDiscount(discount);
    }

    @PostMapping("/public/create/discount")
    public Discount updateDiscount(@RequestBody Discount discount){
        if(discountService.getDiscountById(discount.getEventId()) != null){
            return discountService.saveDiscount(discount);
        }else{
            return null;
        }
    }

    @DeleteMapping("/public/delete/discount/{eventId}")
    public void deleteDiscount(@PathVariable("eventId") Integer eventId){
        discountService.deleteDiscount(eventId);
    }
}
