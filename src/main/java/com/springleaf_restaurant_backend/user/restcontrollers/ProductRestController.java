package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Category;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrderDetail;
import com.springleaf_restaurant_backend.user.entities.MenuItem;
import com.springleaf_restaurant_backend.user.repositories.DeliveryOrderDetailRepository;
import com.springleaf_restaurant_backend.user.repositories.DeliveryOrderRepository;
import com.springleaf_restaurant_backend.user.service.MenuItemService;

@RestController
public class ProductRestController {

    @Autowired
    MenuItemService menuItemService;
    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;
    @Autowired
    DeliveryOrderDetailRepository deliveryOrderDetailRepository;

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
        return menuItemService.saveMenuItem2(menuItem);
    }

    @PutMapping("/public/update/product/{menuItemId}")
    public MenuItem updateMenuItem(@PathVariable("menuItemId") Long menuItemId,
            @RequestBody MenuItem updatedMenuItem) {
        MenuItem databaseMenuItem = menuItemService.getMenuItemById(menuItemId);
        if (databaseMenuItem != null) {
            MenuItem existingMenuItem = databaseMenuItem;
            existingMenuItem.setMenuItemId(updatedMenuItem.getMenuItemId());
            existingMenuItem.setName(updatedMenuItem.getName());
            existingMenuItem.setPrice(updatedMenuItem.getPrice());
            existingMenuItem.setDescription(updatedMenuItem.getDescription());
            existingMenuItem.setImageUrl(updatedMenuItem.getImageUrl());
            existingMenuItem.setCategoryId(updatedMenuItem.getCategoryId());
            existingMenuItem.setStatus(updatedMenuItem.getStatus());
            return menuItemService.saveMenuItem2(existingMenuItem);
        } else {
            return null;
        }
    }

    @DeleteMapping("/public/delete/product/{menuItemId}")
    public void deleteProduct(@PathVariable("menuItemId") Long menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);
    }

    @PostMapping("/product/addToCart")
    public ResponseEntity<String> addToCart(@RequestBody Long productId){
    //     DeliveryOrder deliveryOrder = deliveryOrderRepository.findByUser(Long.valueOf(1));
    //     if(deliveryOrder == null) {
    //         deliveryOrder = new DeliveryOrder();
    //         deliveryOrder.setUser(Long.valueOf(1));
    //         deliveryOrder.setDeliveryAddress(125);
    //         deliveryOrderRepository.save(deliveryOrder);
    //     }
    //     DeliveryOrderDetail deliveryOrderDetail = deliveryOrderDetailRepository.findByDeliveryOrder(deliveryOrder.getDeliveryOrderId());
    //     if(deliveryOrderDetail == null){
    //         deliveryOrderDetail = new DeliveryOrderDetail();
    //         deliveryOrderDetail.setDeliveryOrder(deliveryOrder.getDeliveryOrderId());
    //         deliveryOrderDetailRepository.save(deliveryOrderDetail);
    //     }
    //     if(deliveryOrderDetail.getMenuItem() == productId){
    //         System.out.println("Đã có trong giỏ hàng");
    //         return ResponseEntity.badRequest().body("Đã có trong giỏ hàng");
    //     }else{
    //         deliveryOrderDetail.setDeliveryOrder(deliveryOrder.getDeliveryOrderId());
    //         deliveryOrderDetail.setMenuItem(productId);
    //         deliveryOrderDetail.setOrderTime(new Date());
    //         deliveryOrderDetail.setQuantity(1);
    //         deliveryOrderDetailRepository.save(deliveryOrderDetail);
    //         System.out.println("Thêm thành công");
             return ResponseEntity.ok("Thêm thành công");
    //     }
    }
}
