package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.MergeTable;
import com.springleaf_restaurant_backend.user.repositories.MergeTableRepository;
import com.springleaf_restaurant_backend.user.service.MergeTableService;

import java.util.List;

@Service
public class MergeTableServiceImpl implements MergeTableService {
    private final MergeTableRepository mergeTableRepository;

    public MergeTableServiceImpl(MergeTableRepository mergeTableRepository) {
        this.mergeTableRepository = mergeTableRepository;
    }

    @Override
    public List<MergeTable> getAllMergeTables() {
        return mergeTableRepository.findAll();
    }

    @Override
    public MergeTable getMergeTableById(Long id) {
        return mergeTableRepository.findById(id).orElse(null);
    }

    @Override
    public MergeTable saveMergeTable(MergeTable mergeTable) {
        return mergeTableRepository.save(mergeTable);
    }

    @Override
    public void deleteMergeTable(Long id) { 
        mergeTableRepository.deleteById(id);
    }
}
