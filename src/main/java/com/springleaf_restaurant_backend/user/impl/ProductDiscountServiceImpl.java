package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.ProductDiscount;
import com.springleaf_restaurant_backend.user.repositories.ProductDiscountRepository;
import com.springleaf_restaurant_backend.user.service.ProductDiscountService;

import java.util.List;

@Service
public class ProductDiscountServiceImpl implements ProductDiscountService {
    private final ProductDiscountRepository productDiscountRepository;

    public ProductDiscountServiceImpl(ProductDiscountRepository productDiscountRepository) {
        this.productDiscountRepository = productDiscountRepository;
    }

    @Override
    public List<ProductDiscount> getAllProductDiscounts() {
        return productDiscountRepository.findAll();
    }

    @Override
    public ProductDiscount getProductDiscountById(Long id) {
        return productDiscountRepository.findById(id).orElse(null);
    }

    @Override
    public ProductDiscount saveProductDiscount(ProductDiscount ProductDiscount) {
        return productDiscountRepository.save(ProductDiscount);
    }

    @Override
    public void deleteProductDiscount(Long id) {
        productDiscountRepository.deleteById(id); 
    }

    @Override
    public List<ProductDiscount> getProductDiscountsByUserId(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductDiscountsByUserId'");
    }

    @Override
    public void saveAllProductDiscounts(List<ProductDiscount> ProductDiscounts) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAllProductDiscounts'");
    }
}
