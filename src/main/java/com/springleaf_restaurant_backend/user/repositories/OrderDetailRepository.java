package com.springleaf_restaurant_backend.user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springleaf_restaurant_backend.user.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId);
    
    @Query("SELECT od.menuItemId, COUNT(od.menuItemId) AS orderCount " +
            "FROM OrderDetail od " +
            "GROUP BY od.menuItemId " +
            "ORDER BY orderCount DESC")
    List<Object[]> findTop5MostOrderedItems();
}
