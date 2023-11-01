package com.springleaf_restaurant_backend.user.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Builder
@Table(name = "Majors")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_id")
    private Integer majorId;

    @Column(name = "major_name")
    private String majorName;
}