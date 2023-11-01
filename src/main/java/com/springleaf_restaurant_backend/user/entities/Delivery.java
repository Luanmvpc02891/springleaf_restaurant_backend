package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Deliveries")

public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long deliveryId;

    @Column(name = "inventories_brand_id")
    private Long inventoryBrand;

    @Column(name = "date")
    private Date date;

    @Column(name = "warehouse_manager_id")
    private Long warehouseManager;

    @Column(name = "user_id")
    private Long user;

}
