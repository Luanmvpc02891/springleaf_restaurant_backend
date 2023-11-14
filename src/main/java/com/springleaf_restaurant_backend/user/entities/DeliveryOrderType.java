package com.springleaf_restaurant_backend.user.entities;
import lombok.*;
import jakarta.persistence.*;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Delivery_Order_Type")
public class DeliveryOrderType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_order_type_id")
    private Integer deliveryOrderTypeId;

    @Column(name = "type_name")
    private String typeName;
}
