package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrderDetail;
import com.springleaf_restaurant_backend.user.repositories.DeliveryOrderDetailRepository;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderDetailService;

import java.util.List;

@Service
public class DeliveryOrderDetailServiceImpl implements DeliveryOrderDetailService {
    private final DeliveryOrderDetailRepository deliveryOrderDetailRepository;

    @Autowired
    public DeliveryOrderDetailServiceImpl(DeliveryOrderDetailRepository deliveryOrderDetailRepository) {
        this.deliveryOrderDetailRepository = deliveryOrderDetailRepository;
    }

    @Override
    public DeliveryOrderDetail getDeliveryOrderDetailById(Long id) {
        return deliveryOrderDetailRepository.findById(id).orElse(null);
    }

    @Override
    public List<DeliveryOrderDetail> getAllDeliveryOrderDetails() {
        return deliveryOrderDetailRepository.findAll();
    }

    @Override
    public DeliveryOrderDetail saveDeliveryOrderDetail(DeliveryOrderDetail deliveryOrderDetail) {
        return deliveryOrderDetailRepository.save(deliveryOrderDetail);
    }

    @Override
    public void updateDeliveryOrderDetail(DeliveryOrderDetail deliveryOrderDetail) {
        deliveryOrderDetailRepository.save(deliveryOrderDetail);
    }

    @Override
    public void deleteDeliveryOrderDetail(Long id) {
        deliveryOrderDetailRepository.deleteById(id);
    }
}
