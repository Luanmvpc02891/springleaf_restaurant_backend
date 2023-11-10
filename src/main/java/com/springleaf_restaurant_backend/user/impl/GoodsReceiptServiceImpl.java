package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.GoodsReceipt;
import com.springleaf_restaurant_backend.user.repositories.GoodsReceiptRepository;
import com.springleaf_restaurant_backend.user.service.GoodsReceiptService;

import java.util.List;

@Service
public class GoodsReceiptServiceImpl implements GoodsReceiptService {
    private final GoodsReceiptRepository goodsReceiptRepository;

    @Autowired
    public GoodsReceiptServiceImpl(GoodsReceiptRepository goodsReceiptRepository) {
        this.goodsReceiptRepository = goodsReceiptRepository;
    }

    @Override
    public GoodsReceipt getGoodsReceiptById(Long id) {
        return goodsReceiptRepository.findById(id).orElse(null);
    }

    @Override
    public List<GoodsReceipt> getAllDeliveries() {
        return goodsReceiptRepository.findAll();
    }

    @Override
    public GoodsReceipt saveGoodsReceipt(GoodsReceipt goodsReceipt) {
        return goodsReceiptRepository.save(goodsReceipt);
    }

    @Override
    public GoodsReceipt updateGoodsReceipt(GoodsReceipt goodsReceipt) {
        return goodsReceiptRepository.save(goodsReceipt);
    }

    @Override
    public void deleteGoodsReceipt(Long id) {
        goodsReceiptRepository.deleteById(id);
    }
}
