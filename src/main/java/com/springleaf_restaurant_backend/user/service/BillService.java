package com.springleaf_restaurant_backend.user.service;

import java.util.List;
import java.util.Map;

import com.springleaf_restaurant_backend.user.entities.Bill;
import com.springleaf_restaurant_backend.user.entities.BillDetail;

public interface BillService {
    Bill getBillById(Long id);

    List<Bill> getAllBills();

    Bill saveBill(Bill bill);

    void deleteBill(Long id);

    List<Object[]> getRevenueByTimeRange(String startDate, String endDate);

    List<Bill> getBillsByTimeRange(String startDate, String endDate);

    Double calculateTotalRevenue();

    Long countPaidBills();

    List<BillDetail> getBillDetailsByBillId(Long billId);

    List<Map.Entry<Long, Long>> getMostOrderedItems();

    List<Bill> getBillsByUserId(Long userId);
}
