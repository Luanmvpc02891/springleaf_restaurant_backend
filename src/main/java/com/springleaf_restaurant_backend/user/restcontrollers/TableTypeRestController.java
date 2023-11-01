package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.TableType;
import com.springleaf_restaurant_backend.user.repositories.TableTypeRepository;

@RestController
@RequestMapping("/api")
public class TableTypeRestController {

  @Autowired
  private TableTypeRepository tableTypeRepository;

  @GetMapping("/tableTypes")
  public List<TableType> getTableTypes() {
    return tableTypeRepository.findAll();
  }

  @GetMapping("/tableType/{tableTypeId}")
  public Optional<TableType> getTableTypeById(@PathVariable("tableTypeId") Integer tableTypeId) {
    return tableTypeRepository.findById(tableTypeId);
  }

}
