package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.service.UserService;
import com.springleaf_restaurant_backend.user.entities.Bill;
import com.springleaf_restaurant_backend.user.service.BillService;
import com.springleaf_restaurant_backend.user.service.MonthlyRevenueService;

@RestController
public class BillRestController {
    @Autowired
    private BillService billService;
    @Autowired
    private MonthlyRevenueService monthlyRevenueService;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

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

    @GetMapping("/public/count-paid")
    public Long countPaidBills() {
        return billService.countPaidBills();
    }

    @GetMapping("/public/most-ordered-items")
    public ResponseEntity<List<Map.Entry<Long, Long>>> getMostOrderedItems() {
        List<Map.Entry<Long, Long>> mostOrderedItems = billService.getMostOrderedItems();
        return new ResponseEntity<>(mostOrderedItems, HttpStatus.OK);
    }

    @GetMapping("/public/user/getBillByUser")
    public ResponseEntity<List<Bill>> getUserBillByUser(
            @RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));
        Optional<User> userByToken = userService.findByUsername(username);
        if (userByToken.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            List<Bill> userBills = billService.getBillsByUserId(userByToken.get().getUserId());
            return ResponseEntity.ok(userBills);
        }
    }

}
