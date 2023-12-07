package com.springleaf_restaurant_backend.user.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface MonthlyRevenueService {
  // Map<Integer, Map<Integer, Double>> calculateMonthlyRevenueByYear();
  Map<Integer, Double> calculateMonthlyRevenueByYear(int year);
}
