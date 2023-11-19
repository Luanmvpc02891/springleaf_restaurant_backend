package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.ReceiptDetails;
import com.springleaf_restaurant_backend.user.repositories.ReceiptDetailsRepository;
import com.springleaf_restaurant_backend.user.service.ReceiptDetailsService;

import java.util.List;

@Service
public class ReceiptDetailsServiceImpl implements ReceiptDetailsService {
    private final ReceiptDetailsRepository receiptDetailsRepository;

    public ReceiptDetailsServiceImpl(ReceiptDetailsRepository receiptDetailsRepository) {
        this.receiptDetailsRepository = receiptDetailsRepository;
    }

    @Override
    public List<ReceiptDetails> getAllReceiptDetails() {
        return receiptDetailsRepository.findAll();
    }

    @Override
    public ReceiptDetails getReceiptDetailsById(Long id) {
        return receiptDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public ReceiptDetails saveReceiptDetails(ReceiptDetails receiptDetails) {
        return receiptDetailsRepository.save(receiptDetails);
    }

    @Override
    public void deleteReceiptDetails(Long id) { 
        receiptDetailsRepository.deleteById(id);
    }
}
