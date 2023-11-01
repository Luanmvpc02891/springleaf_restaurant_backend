package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bill_Details")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_detail_id")
    private Long billDetailId;

    @Column(name = "menu_item_id")
    private Long menuItem;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "bill_id")
    private Long bill;

}
