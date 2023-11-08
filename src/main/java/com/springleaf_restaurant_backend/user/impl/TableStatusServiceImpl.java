package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.TableStatus;
import com.springleaf_restaurant_backend.user.repositories.TableStatusRepository;
import com.springleaf_restaurant_backend.user.service.TableStatusService;

import java.util.List;

@Service
public class TableStatusServiceImpl implements TableStatusService {
    private final TableStatusRepository tableStatusRepository;

    @Autowired
    public TableStatusServiceImpl(TableStatusRepository tableStatusRepository) {
        this.tableStatusRepository = tableStatusRepository;
    }

    @Override
    public List<TableStatus> getAllTableStatuses() {
        return tableStatusRepository.findAll();
    }

    @Override
    public TableStatus getTableStatusById(Integer id) {
        return tableStatusRepository.findById(id).orElse(null);
    }

    @Override
    public TableStatus saveTableStatus(TableStatus tableStatus) {
        return tableStatusRepository.save(tableStatus);
    }

    @Override
    public TableStatus updateTableStatus(TableStatus tableStatus) {
        return tableStatusRepository.save(tableStatus);
    }

    @Override
    public void deleteTableStatus(Integer id) {
        tableStatusRepository.deleteById(id);
    }
}
