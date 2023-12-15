package com.springleaf_restaurant_backend.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.Bill;
import com.springleaf_restaurant_backend.user.repositories.BillRepository;
import com.springleaf_restaurant_backend.user.service.BillService;

@Service
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public List<Object[]> getRevenueByTimeRange(String startDate, String endDate) {
        return billRepository.getRevenueByTimeRange(startDate, endDate);
    }

    @Override
    public List<Bill> getBillsByTimeRange(String startDate, String endDate) {
        return billRepository.getBillsByTimeRange(startDate, endDate);
    }

    @Override
    public Double calculateTotalRevenue() {
        List<Bill> bills = billRepository.findAll();
        Double totalRevenue = 0.0;
        for (Bill bill : bills) {
            totalRevenue += bill.getTotalAmount();
        }
        return totalRevenue;
    }

    
}