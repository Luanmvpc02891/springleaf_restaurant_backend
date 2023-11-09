package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.OrderType;
import com.springleaf_restaurant_backend.user.service.OrderTypeService;

@RestController
public class OrderTypeRestController {
    @Autowired
    OrderTypeService orderTypeService;

    @GetMapping("/public/orderTypes")
    public List<OrderType> getOrderTypes() {
        return orderTypeService.getAllOrderTypes();
    }

    @GetMapping("/public/orderType/{orderTypeId}")
    public OrderType getOneOrderType(@PathVariable("OrderTypeId") Long orderTypeId){
        return orderTypeService.getOrderTypeById(orderTypeId);
    }

    @PostMapping("/public/create/orderType")
    public OrderType createOrderType(@RequestBody OrderType orderType){
        return orderTypeService.saveOrderType(orderType);
    }

    @PutMapping("/public/update/orderType")
    public OrderType updateOrderType(@RequestBody OrderType orderType){
        if(orderTypeService.getOrderTypeById(orderType.getOrderTypeId()) != null){
            return orderTypeService.saveOrderType(orderType);
        }else{
            return null;
        }
    }

    @DeleteMapping("/public/delete/orderType/{orderTypeId}")
    public void deleteOrderType(@PathVariable("OrderTypeId") Long orderTypeId){
        orderTypeService.deleteOrderType(orderTypeId);
    }
}
