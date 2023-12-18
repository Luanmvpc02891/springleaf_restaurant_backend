package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.BillDetail;

import java.util.List;

public interface BillDetailService {
    BillDetail getBillDetailById(Long id);

    List<BillDetail> getAllBillDetails();

    BillDetail saveBillDetail(BillDetail billDetail);

    void deleteBillDetail(Long id);

    List<BillDetail> getBillDetailsByBillId(Long billId);

    
}