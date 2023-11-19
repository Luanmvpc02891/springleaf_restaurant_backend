package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.ComboDetail;

import java.util.List;

public interface ComboDetailService {
    ComboDetail getComboDetailById(Long id);

    List<ComboDetail> getAllComboDetails();

    ComboDetail saveComboDetail(ComboDetail comboDetail);

    void deleteComboDetail(Long id);
}
