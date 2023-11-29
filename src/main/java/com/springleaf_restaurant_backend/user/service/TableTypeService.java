package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.TableType;

import java.util.List;

public interface TableTypeService {
    List<TableType> getAllTableTypes();

    TableType getTableTypeById(Integer id);

    TableType saveTableType(TableType tableType);

    void deleteTableType(Integer id);
}
