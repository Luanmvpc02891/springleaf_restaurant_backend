package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.ComboDetail;
import com.springleaf_restaurant_backend.user.repositories.ComboDetailRepository;
import com.springleaf_restaurant_backend.user.service.ComboDetailService;

import java.util.List;

@Service
public class ComboDetailServiceImpl implements ComboDetailService {
    private final ComboDetailRepository comboDetailRepository;

    @Autowired
    public ComboDetailServiceImpl(ComboDetailRepository comboDetailRepository) {
        this.comboDetailRepository = comboDetailRepository;
    }

    @Override
    public ComboDetail getComboDetailById(Long id) {
        return comboDetailRepository.findById(id).orElse(null);
    }

    @Override
    public List<ComboDetail> getAllComboDetails() {
        return comboDetailRepository.findAll();
    }

    @Override
    public ComboDetail saveComboDetail(ComboDetail comboDetail) {
        return comboDetailRepository.save(comboDetail);
    }

    @Override
    public void deleteComboDetail(Long id) {
        comboDetailRepository.deleteById(id);
    }
}

