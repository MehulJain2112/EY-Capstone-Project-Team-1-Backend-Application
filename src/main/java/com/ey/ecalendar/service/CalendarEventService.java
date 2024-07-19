package com.ey.ecalendar.service;

import com.ey.ecalendar.model.CalendarEvent;
import com.ey.ecalendar.repository.CalendarEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarEventService {

	@Autowired
    private CalendarEventRepository calendarEventRepository;

    public List<CalendarEvent> getAllCalendarEvents() {
        return calendarEventRepository.findAll();
    }

    public CalendarEvent getCalendarEventById(Long id) {
        return calendarEventRepository.findById(id).orElse(null);
    }

    public CalendarEvent addCalendarEvent(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }

    public void deleteCalendarEvent(Long id) {
        calendarEventRepository.deleteById(id);
    }

    public CalendarEvent updateCalendarEvent(Long id, CalendarEvent calendarEventDetails) {
        CalendarEvent calendarEvent = calendarEventRepository.findById(id).orElse(null);
        if (calendarEvent != null) {
            calendarEvent.setTitle(calendarEventDetails.getTitle());
            calendarEvent.setDate(calendarEventDetails.getDate());
            calendarEvent.setType(calendarEventDetails.getType());
            calendarEvent.setEmployee(calendarEventDetails.getEmployee());
            return calendarEventRepository.save(calendarEvent);
        } else {
            return null;
        }
    }

}
