package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_discounts")
public class ProductDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_discount_id")
    private Long productDiscountId;

    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "discount_value")
    private Integer discountValue;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "active")
    private boolean active;

}
