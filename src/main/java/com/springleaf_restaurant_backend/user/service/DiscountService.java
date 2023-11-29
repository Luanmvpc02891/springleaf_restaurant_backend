package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.Discount;

import java.util.List;

public interface DiscountService {
    Discount getDiscountById(Integer id);

    List<Discount> getAllDiscounts();

    Discount saveDiscount(Discount discount);

    void deleteDiscount(Integer id);
}
