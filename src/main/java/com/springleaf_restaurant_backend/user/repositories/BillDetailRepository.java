package com.springleaf_restaurant_backend.user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springleaf_restaurant_backend.user.entities.BillDetail;

public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
  @Query("SELECT bd " +
      "FROM BillDetail bd " +
      "WHERE bd.bill = :billId")
  List<BillDetail> getBillDetailsByBillId(Long billId);

  List<BillDetail> findByMenuItemId(Long menuItemId);

}
