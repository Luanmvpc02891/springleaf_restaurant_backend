package com.springleaf_restaurant_backend.user.service;


import java.util.List;

import com.springleaf_restaurant_backend.user.entities.ProductDiscount;

public interface ProductDiscountService {
    List<ProductDiscount> getAllProductDiscounts();

    ProductDiscount getProductDiscountById(Long id);

    ProductDiscount saveProductDiscount(ProductDiscount ProductDiscount);

    void deleteProductDiscount(Long id);

    List<ProductDiscount> getProductDiscountsByUserId(Long userId);

    void saveAllProductDiscounts(List<ProductDiscount> ProductDiscounts);
}
