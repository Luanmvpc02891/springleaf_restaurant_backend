package com.springleaf_restaurant_backend.user.restcontrollers;

import com.springleaf_restaurant_backend.user.entities.ProductDiscount;
import com.springleaf_restaurant_backend.user.service.ProductDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductDiscountRestController {

    @Autowired
    private ProductDiscountService productDiscountService;

    @GetMapping("/public/productDiscounts")
    public List<ProductDiscount> getProductDiscounts() {
        return productDiscountService.getAllProductDiscounts();
    }

    @GetMapping("/public/productDiscount/{productDiscountId}")
    public ProductDiscount getProductDiscount(@PathVariable Long productDiscountId) {
        return productDiscountService.getProductDiscountById(productDiscountId);
    }

    @PostMapping("/public/create/productDiscount")
    public ProductDiscount createProductDiscount(@RequestBody ProductDiscount productDiscount) {
        return productDiscountService.saveProductDiscount(productDiscount);
    }

    @PutMapping("/public/update/productDiscount")
    public ProductDiscount updateProductDiscount(@RequestBody ProductDiscount productDiscount) {
        return productDiscountService.saveProductDiscount(productDiscount);
    }

    @DeleteMapping("/public/delete/productDiscount/{productDiscountId}")
    public void deleteProductDiscount(@PathVariable Long productDiscountId) {
        productDiscountService.deleteProductDiscount(productDiscountId);
    }

    
}
