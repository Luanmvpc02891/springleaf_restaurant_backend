package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.Bill;
import com.springleaf_restaurant_backend.user.repositories.BillRepository;
import com.springleaf_restaurant_backend.user.service.BillService;

import java.util.List;

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
    public void saveBill(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void updateBill(Bill bill) {
        // You can add any specific business logic here before saving the bill.
        billRepository.save(bill);
    }

    @Override
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }
}