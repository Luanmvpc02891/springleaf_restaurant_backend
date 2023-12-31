package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.BillDetail;
import com.springleaf_restaurant_backend.user.service.BillDetailService;

@RestController
public class BillDetailRestController {
    @Autowired
    private BillDetailService billDetailService;

    @GetMapping("/public/billDetails")
    public List<BillDetail> getBilletails() {
        return billDetailService.getAllBillDetails();
    }

    @GetMapping("/public/billDetail/{id}")
    public BillDetail getOneBillDetail(@RequestBody BillDetail billDetail) {
        return billDetailService.getBillDetailById(billDetail.getBillDetailId());
    }

    @PostMapping("/public/create/billDetail")
    public BillDetail postMethodName(@RequestBody BillDetail billDetail) {
        return billDetailService.saveBillDetail(billDetail);
    }

    @PutMapping("/public/update/billDetail")
    public BillDetail updateBillDetail(@RequestBody BillDetail billDetail) {
        return billDetailService.saveBillDetail(billDetail);
    }

    @DeleteMapping("/public/delete/billDetail/{billDetailId}")
    public void deleteBillDetail(@PathVariable("billDetailId") Long billDetailId) {
        billDetailService.deleteBillDetail(billDetailId);
    }

    @GetMapping("/public/billDetails/{billId}/billDetails")
    public List<BillDetail> getBillDetailsByBillId(@PathVariable Long billId) {
        return billDetailService.getBillDetailsByBillId(billId);
    }

}
