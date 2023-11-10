package com.springleaf_restaurant_backend.user.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Restaurant_Tables")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "table_name")
    private String tableName;

    @ManyToOne
    @JoinColumn(name = "table_type_id")
    private TableType tableTypeId;

    @ManyToOne
    @JoinColumn(name = "table_status_id")
    private TableStatus tableStatusId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurantId;

}
