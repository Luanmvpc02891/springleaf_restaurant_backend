package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Combo_Details")
public class ComboDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combo_detail_id")
    private Long comboDetailId;

    @Column(name = "combo_id")
    private Long combo;

    @Column(name = "menu_item_id")
    private Long menuItem;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "combo_type_id")
    private Integer comboTypeId;

}
