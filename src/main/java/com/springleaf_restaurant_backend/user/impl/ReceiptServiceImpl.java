package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.Receipt;
import com.springleaf_restaurant_backend.user.repositories.ReceiptRepository;
import com.springleaf_restaurant_backend.user.service.ReceiptService;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @Override
    public Receipt getReceiptById(Long id) {
        return receiptRepository.findById(id).orElse(null);
    }

    @Override
    public Receipt saveReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id); 
    }
}
