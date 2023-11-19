package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.TableStatus;

import java.util.List;

public interface TableStatusService {
    List<TableStatus> getAllTableStatuses();

    TableStatus getTableStatusById(Integer id);

    TableStatus saveTableStatus(TableStatus tableStatus);

    void deleteTableStatus(Integer id);
}
