package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.GoodsReceipt;

import java.util.List;

public interface GoodsReceiptService {
    GoodsReceipt getGoodsReceiptById(Long id);

    List<GoodsReceipt> getAllDeliveries();

    GoodsReceipt saveGoodsReceipt(GoodsReceipt GoodsReceipt);

    void deleteGoodsReceipt(Long id);
}
