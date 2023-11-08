package com.springleaf_restaurant_backend.user.service;

import java.util.List;

import com.springleaf_restaurant_backend.user.entities.Bill;

public interface BillService {
    Bill getBillById(Long id);
    List<Bill> getAllBills();
    void saveBill(Bill bill);
    void updateBill(Bill bill);
    void deleteBill(Long id);
}
