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

    @Column(name = "table_type_id")
    private Integer tableTypeId;

    @Column(name = "table_status_id")
    private String tableStatusId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "seating_capacity")
    private Integer seatingCapacity;

    @Column(name = "description")
    private String description;

}
