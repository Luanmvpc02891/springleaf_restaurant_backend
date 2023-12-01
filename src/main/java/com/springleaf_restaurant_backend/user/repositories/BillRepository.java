package com.springleaf_restaurant_backend.user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springleaf_restaurant_backend.user.entities.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
  @Query("SELECT b.billTime, SUM(b.totalAmount) " +
      "FROM Bill b " +
      "WHERE b.billTime BETWEEN :startDate AND :endDate " +
      "GROUP BY b.billTime")
  List<Object[]> getRevenueByTimeRange(String startDate, String endDate);

  @Query("SELECT b " +
      "FROM Bill b " +
      "WHERE b.billTime BETWEEN :startDate AND :endDate")
  List<Bill> getBillsByTimeRange(String startDate, String endDate);

}
