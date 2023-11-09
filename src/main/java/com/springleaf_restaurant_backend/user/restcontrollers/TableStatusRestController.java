package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.TableStatus;
import com.springleaf_restaurant_backend.user.service.TableStatusService;

@RestController
public class TableStatusRestController {
    @Autowired
    private TableStatusService tableStatusService;

    @GetMapping("/public/tableStatuses")
    public List<TableStatus> getTableStatuses() {
        return tableStatusService.getAllTableStatuses();
    }

    @GetMapping("/public/tableStatus/{tableStatusId}")
    public TableStatus getTableStatusById(@PathVariable("tableStatusId") Integer tableStatusId) {
        return tableStatusService.getTableStatusById(tableStatusId);
    }

    @PostMapping("/public/create/tableStatus")
    public TableStatus createTableStatus(@RequestBody TableStatus tableStatus){
        return tableStatusService.saveTableStatus(tableStatus);
    }

    @PutMapping("/public/update/tableStatus")
    public TableStatus updateTableStatus(@RequestBody TableStatus tableStatus){
        if(tableStatusService.getTableStatusById(tableStatus.getTableStatusId()) != null){
            return tableStatusService.saveTableStatus(tableStatus);
        }else{
            return null;
        }
    }

    @DeleteMapping("/public/delete/tableStatus/{tableStatusId}")
    public void deleteTableStatus(@PathVariable("tableStatusId") Integer tableStatusId) {
        tableStatusService.deleteTableStatus(tableStatusId);
    }

}
