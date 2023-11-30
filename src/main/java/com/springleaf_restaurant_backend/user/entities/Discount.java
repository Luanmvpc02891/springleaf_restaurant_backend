package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Integer discountId;

    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "discount_value")
    private Integer discountValue;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "discount_code")
    private String discountCode;
}
