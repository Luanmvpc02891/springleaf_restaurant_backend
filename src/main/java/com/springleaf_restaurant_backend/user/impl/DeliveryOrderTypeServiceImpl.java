package com.springleaf_restaurant_backend.user.impl;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrderType;
import com.springleaf_restaurant_backend.user.repositories.DeliveryOrderTypeRepository;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryOrderTypeServiceImpl implements DeliveryOrderTypeService {

    private final DeliveryOrderTypeRepository deliveryOrderTypeRepository;

    @Autowired
    public DeliveryOrderTypeServiceImpl(DeliveryOrderTypeRepository deliveryOrderTypeRepository) {
        this.deliveryOrderTypeRepository = deliveryOrderTypeRepository;
    }

    @Override
    public List<DeliveryOrderType> getAllDeliveryOrderTypes() {
        return deliveryOrderTypeRepository.findAll();
    }

    @Override
    public Optional<DeliveryOrderType> getDeliveryOrderTypeById(Integer id) {
        return deliveryOrderTypeRepository.findById(id);
    }

    @Override
    public Optional<DeliveryOrderType> getDeliveryOrderTypeByName(String name) {
        return deliveryOrderTypeRepository.findByDeliveryOrderTypeName(name);
    }

    @Override
    public DeliveryOrderType createDeliveryOrderType(DeliveryOrderType deliveryOrderType) {
        return deliveryOrderTypeRepository.save(deliveryOrderType);
    }

    @Override
    public DeliveryOrderType updateDeliveryOrderType(Integer id, DeliveryOrderType updatedDeliveryOrderType) {
        Optional<DeliveryOrderType> existingDeliveryOrderType = deliveryOrderTypeRepository.findById(id);
        if (existingDeliveryOrderType.isPresent()) {
            DeliveryOrderType deliveryOrderType = existingDeliveryOrderType.get();
            deliveryOrderType.setDeliveryOrderTypeName(updatedDeliveryOrderType.getDeliveryOrderTypeName());
            return deliveryOrderTypeRepository.save(deliveryOrderType);
        } else {
            return null;
        }
    }

    @Override
    public void deleteDeliveryOrderType(Integer id) {
        deliveryOrderTypeRepository.deleteById(id);
    }
}
