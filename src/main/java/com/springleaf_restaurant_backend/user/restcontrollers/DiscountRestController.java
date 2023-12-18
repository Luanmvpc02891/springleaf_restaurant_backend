package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.service.DiscountService;
import com.springleaf_restaurant_backend.user.service.MenuItemService;
import com.springleaf_restaurant_backend.user.entities.Discount;
import com.springleaf_restaurant_backend.user.entities.MenuItem;
import com.springleaf_restaurant_backend.user.entities.MessageResponse;

@RestController
public class DiscountRestController {
    @Autowired
    DiscountService discountService;
    @Autowired
    MenuItemService menuItemService;

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

    @PutMapping("/public/update/discount")
    public Discount updateDiscount(@RequestBody Discount discount){
        return discountService.saveDiscount(discount);
    }

    @DeleteMapping("/public/delete/discount/{eventId}")
    public void deleteDiscount(@PathVariable("eventId") Integer eventId){
        discountService.deleteDiscount(eventId);
    }

    @PostMapping("/public/create/getDiscountByDiscountNameAndMenuItem/{discountCode}")
    public ResponseEntity<MessageResponse> getDiscount(
        @RequestHeader("Authorization") String jwtToken,
        @PathVariable("discountCode") String discountCode,
        @RequestBody List<Long> menuItemIds
    ){
        System.out.println(jwtToken);
        Discount discount = discountService.getDiscountByDiscountCode(discountCode);
        MessageResponse message = new MessageResponse();
        if(discount != null){
            if(discount.isActive()){
                for (Long menuItemId : menuItemIds) {
                    if(menuItemId == discount.getMenuItemId() && (discount.getDiscountType()).equals("private")){
                        MenuItem item = menuItemService.getMenuItemById(menuItemId);
                        Double discountPrice = (item.getUnitPrice() * discount.getDiscountValue())/100;
                        message.setMessage(String.valueOf(discountPrice));
                        return ResponseEntity.ok(message);
                    }                    
                }
                return ResponseEntity.ok(null);
            }else{
                message.setMessage("Discount is Experied");
                return ResponseEntity.ok(message);
            }
        }else{
            message.setMessage("Discount is not valid");
            return ResponseEntity.ok(message);
        }
    }
}
