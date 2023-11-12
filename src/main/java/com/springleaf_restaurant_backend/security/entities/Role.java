package com.springleaf_restaurant_backend.security.entities;

import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_detail")
    private String roleDetail;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    List<UserRole> userRoles;
}
