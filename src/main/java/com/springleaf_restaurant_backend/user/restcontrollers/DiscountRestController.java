package com.springleaf_restaurant_backend.user.restcontrollers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.service.DiscountService;
import com.springleaf_restaurant_backend.user.service.MenuItemService;
import com.springleaf_restaurant_backend.user.entities.Discount;
import com.springleaf_restaurant_backend.user.entities.MessageResponse;

@RestController
public class DiscountRestController {
    @Autowired
    DiscountService discountService;
    @Autowired
    MenuItemService menuItemService;

    @GetMapping("/public/discounts")
    public List<Discount> getDiscounts() {
        return discountService.getAllDiscounts();
    }

    @GetMapping("/public/discount/{eventId}")
    public Discount getDiscount(@PathVariable("eventId") Integer eventId) {
        return discountService.getDiscountById(eventId);
    }

    @PostMapping("/public/create/discount")
    public Discount createDiscount(@RequestBody Discount discount) {
        return discountService.saveDiscount(discount);
    }

    @PostMapping("/public/update/discount")
    public Discount updateDiscount(@RequestBody Discount discount) {
        return discountService.saveDiscount(discount);
    }

    @DeleteMapping("/public/delete/discount/{eventId}")
    public void deleteDiscount(@PathVariable("eventId") Integer eventId) {
        discountService.deleteDiscount(eventId);
    }

    @PostMapping("/public/create/getDiscountByDiscountNameAndMenuItem/{discountCode}")
    public ResponseEntity<MessageResponse> getDiscount(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable("discountCode") String discountCode,
            @RequestBody Double totalPrice) throws ParseException {
        Discount discount = discountService.getDiscountByDiscountCode(discountCode);
        MessageResponse message = new MessageResponse();

        if (discount != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date endDateDiscount = dateFormat.parse(discount.getEndDate());
            Date startDateDiscount = dateFormat.parse(discount.getStartDate());

            LocalDate currentDate = LocalDate.now();
            LocalDate endDiscountDate = endDateDiscount.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startDiscountDate = startDateDiscount.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (currentDate.isAfter(endDiscountDate)) {
                message.setMessage("Discount has expired");
                return ResponseEntity.ok(message);
            }
            else if (currentDate.isBefore(startDiscountDate)) {
                message.setMessage("Discount has not start");
                return ResponseEntity.ok(message);
            } else if (discount.isActive() && discount.getLimitValue() <= totalPrice) {
                // Calculate discount price
                Double discountPrice = (totalPrice * discount.getDiscountValue()) / 100;
                message.setMessage(String.valueOf(discountPrice));
                return ResponseEntity.ok(message);
            } else {
                message.setMessage("Discount is not applicable");
                return ResponseEntity.ok(message);
            }

        } else {
            message.setMessage("Discount is not valid");
            return ResponseEntity.ok(message);
        }
    }
}
