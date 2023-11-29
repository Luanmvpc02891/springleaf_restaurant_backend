package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.Event;

import java.util.List;

public interface EventService {
    Event getEventById(Long id);

    List<Event> getAllEvents();

    Event saveEvent(Event event);

    void deleteEvent(Long id);
}
