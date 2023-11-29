package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Event;
import com.springleaf_restaurant_backend.user.service.EventService;

@RestController
public class EventRestController {
    @Autowired
    private EventService eventService;

    @GetMapping("/public/events")
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/public/event/{eventId}")
    public Event getOneEvent(@PathVariable("eventId") Long eventId) {
        return eventService.getEventById(eventId);
    }

    @PostMapping("/public/create/event")
    public Event createEvent(@RequestBody Event event){
        return eventService.saveEvent(event);
    }

    @PutMapping("/public/update/event")
    public Event updateEvent(@RequestBody Event event){
        return eventService.saveEvent(event);
    }

    @DeleteMapping("/public/delete/event/{eventId}")
    public void delelteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEvent(eventId);
    }
}
