package com.springleaf_restaurant_backend.user.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private Long menuItemId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "bill_id")
    private Long bill;

}
