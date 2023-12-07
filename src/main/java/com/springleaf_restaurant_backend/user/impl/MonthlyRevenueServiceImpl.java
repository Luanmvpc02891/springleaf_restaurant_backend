package com.springleaf_restaurant_backend.user.impl;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.Bill;
import com.springleaf_restaurant_backend.user.repositories.BillRepository;
import com.springleaf_restaurant_backend.user.service.MonthlyRevenueService;

@Service
public class MonthlyRevenueServiceImpl implements MonthlyRevenueService {
    private final BillRepository billRepository;

    @Autowired
    public MonthlyRevenueServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    // @Override
    // public Map<Integer, Map<Integer, Double>> calculateMonthlyRevenueByYear() {
    //     List<Bill> bills = billRepository.findAll();
    //     Map<Integer, Map<Integer, Double>> yearlyRevenue = new HashMap<>();

    //     // Group bills by year
    //     Map<Integer, List<Bill>> billsByYear = bills.stream()
    //         .collect(Collectors.groupingBy(bill -> Integer.parseInt(bill.getBillTime().split("-")[0])));

    //     // Calculate monthly revenue for each year
    //     for (Map.Entry<Integer, List<Bill>> entry : billsByYear.entrySet()) {
    //         int year = entry.getKey();
    //         List<Bill> billsOfYear = entry.getValue();
    //         Map<Integer, Double> monthlyRevenue = new HashMap<>();

    //         // Group bills for each month
    //         Map<Integer, List<Bill>> billsByMonth = billsOfYear.stream()
    //             .collect(Collectors.groupingBy(bill -> Integer.parseInt(bill.getBillTime().split("-")[1])));

    //         // Calculate revenue for each month
    //         for (int month = 1; month <= 12; month++) {
    //             List<Bill> billsOfMonth = billsByMonth.getOrDefault(month, null);
    //             double revenue = (billsOfMonth != null) ? billsOfMonth.stream().mapToDouble(Bill::getTotalAmount).sum() : 0.0;
    //             monthlyRevenue.put(month, revenue);
    //         }

    //         yearlyRevenue.put(year, monthlyRevenue);
    //     }

    //     return yearlyRevenue;
    // }

    @Override
    public Map<Integer, Double> calculateMonthlyRevenueByYear(int year) {
        List<Bill> bills = billRepository.findAllByBillTimeStartingWith(String.valueOf(year));

        Map<Integer, Double> monthlyRevenue = new HashMap<>();

        // Group bills by month and calculate total revenue for each month
        for (int month = 1; month <= 12; month++) {
            final int currentMonth = month;
            double revenue = bills.stream()
                .filter(bill -> Integer.parseInt(bill.getBillTime().split("-")[1]) == currentMonth)
                .mapToDouble(Bill::getTotalAmount)
                .sum();
            monthlyRevenue.put(month, revenue);
        }

        return monthlyRevenue;
    }
}
