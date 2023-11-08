package com.springleaf_restaurant_backend.user.impl;

import com.springleaf_restaurant_backend.user.entities.DeliveryDetail;
import com.springleaf_restaurant_backend.user.repositories.DeliveryDetailRepository;
import com.springleaf_restaurant_backend.user.service.DeliveryDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryDetailServiceImpl implements DeliveryDetailService {
    private final DeliveryDetailRepository deliveryDetailRepository;

    @Autowired
    public DeliveryDetailServiceImpl(DeliveryDetailRepository deliveryDetailRepository) {
        this.deliveryDetailRepository = deliveryDetailRepository;
    }

    @Override
    public DeliveryDetail getDeliveryDetailById(Long id) {
        return deliveryDetailRepository.findById(id).orElse(null);
    }

    @Override
    public List<DeliveryDetail> getAllDeliveryDetails() {
        return deliveryDetailRepository.findAll();
    }

    @Override
    public DeliveryDetail saveDeliveryDetail(DeliveryDetail deliveryDetail) {
        return deliveryDetailRepository.save(deliveryDetail);
    }

    @Override
    public DeliveryDetail updateDeliveryDetail(DeliveryDetail deliveryDetail) {
        return deliveryDetailRepository.save(deliveryDetail);
    }

    @Override
    public void deleteDeliveryDetail(Long id) {
        deliveryDetailRepository.deleteById(id);
    }
}
