package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Integer eventId;

    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "discount_value")
    private Integer discountValue;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;
}
