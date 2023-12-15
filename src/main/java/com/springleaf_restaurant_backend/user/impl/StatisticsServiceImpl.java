package com.springleaf_restaurant_backend.user.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.repositories.BillRepository;
import com.springleaf_restaurant_backend.user.repositories.ReservationRepository;
import com.springleaf_restaurant_backend.user.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {
 private final BillRepository billRepository;

    public StatisticsServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

   
   
}
