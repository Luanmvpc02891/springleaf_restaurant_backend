package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.GoodsReceiptDetails;

import java.util.List;

public interface GoodsReceiptDetailsService {
    GoodsReceiptDetails getGoodsReceiptDetailsById(Long id);
    List<GoodsReceiptDetails> getAllGoodsReceiptDetailss();
    GoodsReceiptDetails saveGoodsReceiptDetails(GoodsReceiptDetails GoodsReceiptDetails);
    GoodsReceiptDetails updateGoodsReceiptDetails(GoodsReceiptDetails GoodsReceiptDetails);
    void deleteGoodsReceiptDetails(Long id);
}
