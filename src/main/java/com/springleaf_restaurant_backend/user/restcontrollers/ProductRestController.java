package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.service.UserService;
import com.springleaf_restaurant_backend.user.entities.Category;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;
import com.springleaf_restaurant_backend.user.entities.MenuItem;
import com.springleaf_restaurant_backend.user.entities.Order;
import com.springleaf_restaurant_backend.user.entities.OrderDetail;
import com.springleaf_restaurant_backend.user.repositories.DeliveryOrderRepository;
import com.springleaf_restaurant_backend.user.service.CategoryService;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderService;
import com.springleaf_restaurant_backend.user.service.MenuItemService;
import com.springleaf_restaurant_backend.user.service.OrderService;

@RestController
public class ProductRestController {

    @Autowired
    MenuItemService menuItemService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    DeliveryOrderService deliveryOrderService;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @GetMapping("/public/products")
    public List<MenuItem> getCategories() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/public/products/{id}")
    public MenuItem getProductById(@PathVariable("id") Long productId) {
        return menuItemService.getMenuItemById(productId);
    }

    @GetMapping("/public/category/{id}/products")
    public List<MenuItem> getProductByCategoryId(@PathVariable("id") Category categoryId) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByCategoryId(categoryId);
        return menuItems;
    }

    @PostMapping("/public/create/product")
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.saveMenuItem(menuItem);
    }

    @PutMapping("/public/update/product/{menuItemId}")
    public MenuItem updateMenuItem(@RequestBody MenuItem updatedMenuItem) {
        return menuItemService.saveMenuItem(updatedMenuItem);
    }

    @DeleteMapping("/public/delete/product/{menuItemId}")
    public void deleteProduct(@PathVariable("menuItemId") Long menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);
    }

    @PostMapping("/public/product/addToCart")
    public ResponseEntity<String> addToCart(
        @RequestHeader("header") String header, @RequestBody List<Long> body)
        {
        String jwt = header.substring(7);
        if(jwtService.isTokenExpired(jwt)){
            Optional<User> user = userService.findByUsername(jwtService.extractUsername(jwt));
            if(user != null){
                Order order = new Order();
                OrderDetail orderDetail = new OrderDetail();
                DeliveryOrder deliveryOrder = new DeliveryOrder();
                deliveryOrder.setCustomerId(body.get(0));
                //deliveryOrder.setDeliveryAddress(Integer.valueOf(body.get(1)));
                deliveryOrder.setDeliveryOrderStatusId(null);
                deliveryOrder.setDeliveryOrderTypeId(null);
                deliveryOrderService.saveDeliveryOrder(deliveryOrder);
                order.setDeliveryOrderId(deliveryOrder.getDeliveryOrderId());
                order.setReservationId(null);
                order.setStaffId(null);
                order.setCombo(null);
                order.setOrderDate(new Date());
                order.setStatus(true);
                order.setTotalAmount(null);
            }
        }
        return ResponseEntity.ok("Thêm thành công");
    }
}
