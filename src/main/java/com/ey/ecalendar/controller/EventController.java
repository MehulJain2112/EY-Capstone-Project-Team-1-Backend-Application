package com.ey.ecalendar.controller;

import com.ey.ecalendar.model.CalendarEvent;
import com.ey.ecalendar.model.OrganizationalEvent;
import com.ey.ecalendar.service.CalendarEventService;
import com.ey.ecalendar.service.OrganizationalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    @Autowired
    private CalendarEventService calendarEventService;

    @Autowired
    private OrganizationalEventService organizationalEventService;

    @GetMapping("/calendar")
    public List<CalendarEvent> getAllCalendarEvents() {
        return calendarEventService.getAllCalendarEvents();
    }

    @GetMapping("/calendar/{id}")
    public ResponseEntity<CalendarEvent> getCalendarEventById(@PathVariable Long id) {
        CalendarEvent calendarEvent = calendarEventService.getCalendarEventById(id);
        if (calendarEvent != null) {
            return ResponseEntity.ok(calendarEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/calendar")
    public CalendarEvent createCalendarEvent(@RequestBody CalendarEvent calendarEvent) {
        return calendarEventService.addCalendarEvent(calendarEvent);
    }

    @PutMapping("/calendar/{id}")
    public ResponseEntity<CalendarEvent> updateCalendarEvent(@PathVariable Long id, @RequestBody CalendarEvent calendarEventDetails) {
        CalendarEvent updatedCalendarEvent = calendarEventService.updateCalendarEvent(id, calendarEventDetails);
        if (updatedCalendarEvent != null) {
            return ResponseEntity.ok(updatedCalendarEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/calendar/{id}")
    public ResponseEntity<Void> deleteCalendarEvent(@PathVariable Long id) {
        CalendarEvent calendarEvent = calendarEventService.getCalendarEventById(id);
        if (calendarEvent != null) {
            calendarEventService.deleteCalendarEvent(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/organizational")
    public List<OrganizationalEvent> getAllOrganizationalEvents() {
        return organizationalEventService.getAllOrganizationalEvents();
    }

    @GetMapping("/organizational/{id}")
    public ResponseEntity<OrganizationalEvent> getOrganizationalEventById(@PathVariable Long id) {
        OrganizationalEvent organizationalEvent = organizationalEventService.getOrganizationalEventById(id);
        if (organizationalEvent != null) {
            return ResponseEntity.ok(organizationalEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/organizational")
    public OrganizationalEvent createOrganizationalEvent(@RequestBody OrganizationalEvent organizationalEvent) {
        return organizationalEventService.addOrganizationalEvent(organizationalEvent);
    }

    @PutMapping("/organizational/{id}")
    public ResponseEntity<OrganizationalEvent> updateOrganizationalEvent(@PathVariable Long id, @RequestBody OrganizationalEvent organizationalEventDetails) {
        OrganizationalEvent updatedOrganizationalEvent = organizationalEventService.updateOrganizationalEvent(id, organizationalEventDetails);
        if (updatedOrganizationalEvent != null) {
            return ResponseEntity.ok(updatedOrganizationalEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/organizational/{id}")
    public ResponseEntity<Void> deleteOrganizationalEvent(@PathVariable Long id) {
        OrganizationalEvent organizationalEvent = organizationalEventService.getOrganizationalEventById(id);
        if (organizationalEvent != null) {
            organizationalEventService.deleteOrganizationalEvent(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
