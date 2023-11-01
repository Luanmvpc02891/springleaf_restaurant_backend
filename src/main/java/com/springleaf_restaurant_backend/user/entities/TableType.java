package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Table_Types")
public class TableType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "table_type_id")
  private Integer tableTypeId;

  @Column(name = "type_name")
  private String name;

}
