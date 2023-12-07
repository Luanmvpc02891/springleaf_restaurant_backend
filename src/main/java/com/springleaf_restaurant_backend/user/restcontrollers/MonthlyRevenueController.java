package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.service.MonthlyRevenueService;

@RestController
public class MonthlyRevenueController {

  private final MonthlyRevenueService monthlyRevenueService;

  @Autowired
  public MonthlyRevenueController(MonthlyRevenueService monthlyRevenueService) {
    this.monthlyRevenueService = monthlyRevenueService;
  }

  @GetMapping("/public/year/{year}")
    public Map<Integer, Double> getMonthlyRevenueByYear(@PathVariable int year) {
        return monthlyRevenueService.calculateMonthlyRevenueByYear(year);
    }
}