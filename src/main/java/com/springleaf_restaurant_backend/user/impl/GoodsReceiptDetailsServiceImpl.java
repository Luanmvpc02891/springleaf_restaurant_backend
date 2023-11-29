package com.springleaf_restaurant_backend.user.impl;

import com.springleaf_restaurant_backend.user.entities.GoodsReceiptDetails;
import com.springleaf_restaurant_backend.user.repositories.GoodsReceiptDetailsRepository;
import com.springleaf_restaurant_backend.user.service.GoodsReceiptDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsReceiptDetailsServiceImpl implements GoodsReceiptDetailsService {
    private final GoodsReceiptDetailsRepository goodsReceiptDetailsRepository;

    public GoodsReceiptDetailsServiceImpl(GoodsReceiptDetailsRepository goodsReceiptDetailsRepository) {
        this.goodsReceiptDetailsRepository = goodsReceiptDetailsRepository;
    }

    @Override
    public GoodsReceiptDetails getGoodsReceiptDetailsById(Long id) {
        return goodsReceiptDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public List<GoodsReceiptDetails> getAllGoodsReceiptDetailss() {
        return goodsReceiptDetailsRepository.findAll();
    }

    @Override
    public GoodsReceiptDetails saveGoodsReceiptDetails(GoodsReceiptDetails GoodsReceiptDetails) {
        return goodsReceiptDetailsRepository.save(GoodsReceiptDetails);
    }

    @Override
    public void deleteGoodsReceiptDetails(Long id) { 
        goodsReceiptDetailsRepository.deleteById(id);
    }
}
