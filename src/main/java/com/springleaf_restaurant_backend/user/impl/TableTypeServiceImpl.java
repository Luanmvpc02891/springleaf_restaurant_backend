package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.TableType;
import com.springleaf_restaurant_backend.user.repositories.TableTypeRepository;
import com.springleaf_restaurant_backend.user.service.TableTypeService;

import java.util.List;

@Service
public class TableTypeServiceImpl implements TableTypeService {
    private final TableTypeRepository tableTypeRepository;

    @Autowired
    public TableTypeServiceImpl(TableTypeRepository tableTypeRepository) {
        this.tableTypeRepository = tableTypeRepository;
    }

    @Override
    public List<TableType> getAllTableTypes() {
        return tableTypeRepository.findAll();
    }

    @Override
    public TableType getTableTypeById(Integer id) {
        return tableTypeRepository.findById(id).orElse(null);
    }

    @Override
    public TableType saveTableType(TableType tableType) {
        return tableTypeRepository.save(tableType);
    }

    @Override
    public TableType updateTableType(TableType tableType) {
        return tableTypeRepository.save(tableType);
    }

    @Override
    public void deleteTableType(Integer id) {
        tableTypeRepository.deleteById(id);
    }
}
