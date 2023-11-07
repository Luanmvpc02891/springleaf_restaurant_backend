package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.MergeTable;
import com.springleaf_restaurant_backend.user.repositories.MergeTableRepository;

@RestController
@RequestMapping("/api/public")
public class MergeTableRestController {
    @Autowired
    private MergeTableRepository mergeTableRepository;

    @GetMapping("/mergeTables")
    public List<MergeTable> gMergeTables() {
        return mergeTableRepository.findAll();
    }
}
