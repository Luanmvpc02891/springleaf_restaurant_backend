package com.springleaf_restaurant_backend.user.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {
    private int orderTotal;
    private String orderInfo;
}
