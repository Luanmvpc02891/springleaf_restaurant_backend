package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.Event;
import com.springleaf_restaurant_backend.user.entities.InventoryBranch;
import com.springleaf_restaurant_backend.user.repositories.EventRepository;

@RestController
@RequestMapping("/api")
public class EventRestController {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/events")
    public List<Event> getInventoryBranchs() {
        return eventRepository.findAll();
    }
}
