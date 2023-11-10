package com.springleaf_restaurant_backend.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;
import com.springleaf_restaurant_backend.user.entities.User;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {

    DeliveryOrder findByUser(User user);

}
