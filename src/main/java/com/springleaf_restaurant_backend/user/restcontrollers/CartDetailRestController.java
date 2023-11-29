package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.OrderDetail;
import com.springleaf_restaurant_backend.user.service.OrderDetailService;

@RestController
public class CartDetailRestController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/public/cartDetails")
    public List<OrderDetail> getOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    @GetMapping("/public/cartDetail/{cartDetailId}")
    public OrderDetail getOneOrderDetail(@PathVariable("cartDetailId") Long cartDetailId){
        return orderDetailService.getOrderDetailById(cartDetailId);
    }

    @PostMapping("/public/create/cartDetail")
    public OrderDetail createCartDetail(@RequestBody OrderDetail orderDetail){
        return orderDetailService.saveOrderDetail(orderDetail);
    }

    @PutMapping("/public/update/cartDetail")
    public OrderDetail updateCartDetail(@RequestBody OrderDetail orderDetail){
        return orderDetailService.saveOrderDetail(orderDetail);
    }

    @DeleteMapping("/public/delete/cartDetail/{cartDetailId}")
    public void deleteOrderDetail(@PathVariable("cartDetailId") Long cartDetailId){
        orderDetailService.deleteOrderDetail(cartDetailId);
    }
}
