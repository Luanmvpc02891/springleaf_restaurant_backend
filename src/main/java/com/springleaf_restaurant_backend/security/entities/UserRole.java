package com.springleaf_restaurant_backend.security.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;
    
    @Column(name = "user_id")
    private Long user;
    
    @Column(name = "role_id")
    private Integer role;
}
