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
    private Integer tableStatusId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

}
