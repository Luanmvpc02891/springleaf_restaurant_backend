package com.springleaf_restaurant_backend.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
