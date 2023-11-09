package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.MergeTable;
import com.springleaf_restaurant_backend.user.service.MergeTableService;

@RestController
public class MergeTableRestController {
    @Autowired
    private MergeTableService mergeTableService;

    @GetMapping("/public/mergeTables")
    public List<MergeTable> getAllMergeTables() {
        return mergeTableService.getAllMergeTables();
    }

    @GetMapping("/public/mergeTables/{mergeTableId}")
    public MergeTable getOneMergeTable(@PathVariable("mergeTableId") Long mergeTableId){
        return mergeTableService.getMergeTableById(mergeTableId);
    }

    @PostMapping("/public/create/mergeTables")
    public MergeTable createMergeTable(@RequestBody MergeTable mergeTable){
        return mergeTableService.saveMergeTable(mergeTable);
    }

    @PutMapping("/public/update/mergeTable")
    public MergeTable updateMergeTable(@RequestBody MergeTable mergeTable){
        if(mergeTableService.getMergeTableById(mergeTable.getId()) != null){
            return mergeTableService.saveMergeTable(mergeTable);
        }else{
            return null;
        }
    }

    @DeleteMapping("/public/delete/mergeTable/{mergeTableId}")
    public void delelteMergeTable(@PathVariable("mergeTableId") Long mergeTableId){
        mergeTableService.deleteMergeTable(mergeTableId);
    }
}
