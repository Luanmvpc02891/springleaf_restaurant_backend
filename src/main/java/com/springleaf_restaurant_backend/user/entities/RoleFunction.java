package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Role_Functions")
public class RoleFunction {
    @Id
    @Column(name = "role_function_id")
    private Integer roleFunctionId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
