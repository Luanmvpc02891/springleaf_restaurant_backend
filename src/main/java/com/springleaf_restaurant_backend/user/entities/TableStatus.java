package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Table_Status")
public class TableStatus {
    @Id
    @Column(name = "table_status_id")
    private Integer tableStatusId;

    @Column(name = "name")
    private String name;

}
