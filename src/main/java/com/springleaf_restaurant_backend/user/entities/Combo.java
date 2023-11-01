package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Combos")
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combo_id")
    private Long comboId;

    @Column(name = "combo_name")
    private String comboName;

    @Column(name = "combo_user")
    private Long comboUser;

    // @Column(name = "combo_item")
    // private Long comboItem;

    @Column(name = "total_amount")
    private Double totalAmount;

}
