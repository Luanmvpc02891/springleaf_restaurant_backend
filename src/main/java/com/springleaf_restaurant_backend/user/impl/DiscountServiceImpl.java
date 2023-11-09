package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.Discount;
import com.springleaf_restaurant_backend.user.repositories.DiscountRepository;
import com.springleaf_restaurant_backend.user.service.DiscountService;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Discount getDiscountById(Integer id) {
        return discountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount saveDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public Discount updateDiscount(Discount discount) {
        // Bạn có thể thêm logic kinh doanh cụ thể tại đây trước khi lưu discount.
        return discountRepository.save(discount);
    }

    @Override
    public void deleteDiscount(Integer id) {
        discountRepository.deleteById(id);
    }
}
