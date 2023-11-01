package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Merge_Tables")
public class MergeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "table_id")
    private Long table;

    @Column(name = "merge_table_id")
    private String mergeTableId;

    @Column(name = "merge_time")
    private Date mergeTime;

}
