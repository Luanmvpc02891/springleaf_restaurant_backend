package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.TableType;
import com.springleaf_restaurant_backend.user.service.TableTypeService;

@RestController
public class TableTypeRestController {

  @Autowired
  private TableTypeService tableTypeService;

  @GetMapping("/public/tableTypes")
  public List<TableType> getTableTypes() {
    return tableTypeService.getAllTableTypes();
  }

  @GetMapping("/public/tableType/{tableTypeId}")
  public TableType getTableTypeById(@PathVariable("tableTypeId") Integer tableTypeId) {
    return tableTypeService.getTableTypeById(tableTypeId);
  }

  @PostMapping("/public/create/tableType")
  public TableType createTableType(@RequestBody TableType tableType){
    return tableTypeService.saveTableType(tableType);
  }

  @PutMapping("/public/update/tableType")
  public TableType updateTableType(@RequestBody TableType tableType){
    return tableTypeService.saveTableType(tableType);
  }

  @DeleteMapping("/public/delete/tableType/{tableTypeId}")
  public void deleteTableTypeById(@PathVariable("tableTypeId") Integer tableTypeId) {
    tableTypeService.deleteTableType(tableTypeId);
  }

}
