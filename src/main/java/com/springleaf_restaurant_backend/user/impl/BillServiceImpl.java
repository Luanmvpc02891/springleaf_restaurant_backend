package com.springleaf_restaurant_backend.user.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.Bill;
import com.springleaf_restaurant_backend.user.entities.BillDetail;
import com.springleaf_restaurant_backend.user.repositories.BillDetailRepository;
import com.springleaf_restaurant_backend.user.repositories.BillRepository;
import com.springleaf_restaurant_backend.user.repositories.CategoryRepository;
import com.springleaf_restaurant_backend.user.repositories.MenuItemRepository;
import com.springleaf_restaurant_backend.user.service.BillService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class BillServiceImpl implements BillService {
    private final EntityManager entityManager;

    @Autowired
    public BillServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public List<Object[]> getRevenueByTimeRange(String startDate, String endDate) {
        return billRepository.getRevenueByTimeRange(startDate, endDate);
    }

    @Override
    public List<Bill> getBillsByTimeRange(String startDate, String endDate) {
        return billRepository.getBillsByTimeRange(startDate, endDate);
    }

    @Override
    public Double calculateTotalRevenue() {
        List<Bill> bills = billRepository.findAll();
        Double totalRevenue = 0.0;
        for (Bill bill : bills) {
            totalRevenue += bill.getTotalAmount();
        }
        return totalRevenue;
    }

    @Override
    public Long countPaidBills() {
        return billRepository.countPaidBills();
    }

    @Override
    public List<BillDetail> getBillDetailsByBillId(Long billId) {
        return billDetailRepository.getBillDetailsByBillId(billId);
    }

    @Override
    public List<Map.Entry<Long, Long>> getMostOrderedItems() {
        String jpql = "SELECT bd.menuItemId, SUM(bd.quantity) " +
                "FROM BillDetail bd " +
                "GROUP BY bd.menuItemId " +
                "ORDER BY SUM(bd.quantity) DESC";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resultList = query.getResultList();

        // Convert query result to a list of Map.Entry<MenuItemId, TotalQuantity>
        List<Map.Entry<Long, Long>> mostOrderedItems = resultList.stream()
                .map(result -> Map.entry((Long) result[0], (Long) result[1]))
                .collect(Collectors.toList());

        return mostOrderedItems;
    }

    @Override
    public List<Bill> getBillsByUserId(Long userId) {
        return billRepository.findByUserId(userId);
    }
}