package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.Bill;
import com.springleaf_restaurant_backend.user.service.BillService;
import com.springleaf_restaurant_backend.user.service.MonthlyRevenueService;

@RestController
public class BillRestController {
    @Autowired
    private BillService billService;
    @Autowired
    private MonthlyRevenueService monthlyRevenueService;

   
    @GetMapping("/public/bills")
    public List<Bill> getBills() {
        return billService.getAllBills();
    }

    @GetMapping("/public/bill/{billId}")
    public Bill getOneBill(@PathVariable("billId") Long billId) {
        return billService.getBillById(billId);
    }

    @PostMapping("/public/create/bill")
    public Bill createBill(@RequestBody Bill bill) {
        return billService.saveBill(bill);
    }

    @PutMapping("/public/update/bill")
    public Bill updateBill(@RequestBody Bill bill) {
        return billService.saveBill(bill);
    }

    @DeleteMapping("/public/delete/bill/{billId}")
    public void deleteBill(@PathVariable("billId") Long billId) {
        billService.deleteBill(billId);
    }

    @GetMapping("/public/revenue")
    public List<Object[]> getRevenueByTimeRange(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return billService.getRevenueByTimeRange(startDate, endDate);
    }

    @GetMapping("/public/billTimeRange")
    public List<Bill> getBillsByTimeRange(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return billService.getBillsByTimeRange(startDate, endDate);
    }

    @GetMapping("/public/totalRevenue")
    public Double getTotalRevenue() {
        return billService.calculateTotalRevenue();
    }

    // @GetMapping("/public/byYear1")
    // public Map<Integer, Map<Integer, Double>> getMonthlyRevenueByYear() {
    //     return monthlyRevenueService.calculateMonthlyRevenueByYear();
    // }

    
}
