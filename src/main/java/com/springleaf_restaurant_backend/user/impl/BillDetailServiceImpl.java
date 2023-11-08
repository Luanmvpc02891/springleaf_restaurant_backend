package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.BillDetail;
import com.springleaf_restaurant_backend.user.repositories.BillDetailRepository;
import com.springleaf_restaurant_backend.user.service.BillDetailService;

import java.util.List;

@Service
public class BillDetailServiceImpl implements BillDetailService {
    private final BillDetailRepository billDetailRepository;

    @Autowired
    public BillDetailServiceImpl(BillDetailRepository billDetailRepository) {
        this.billDetailRepository = billDetailRepository;
    }

    @Override
    public BillDetail getBillDetailById(Long id) {
        return billDetailRepository.findById(id).orElse(null);
    }

    @Override
    public List<BillDetail> getAllBillDetails() {
        return billDetailRepository.findAll();
    }

    @Override
    public void saveBillDetail(BillDetail billDetail) {
        billDetailRepository.save(billDetail);
    }

    @Override
    public void updateBillDetail(BillDetail billDetail) {
        // You can add any specific business logic here before saving the bill detail.
        billDetailRepository.save(billDetail);
    }

    @Override
    public void deleteBillDetail(Long id) {
        billDetailRepository.deleteById(id);
    }
}
