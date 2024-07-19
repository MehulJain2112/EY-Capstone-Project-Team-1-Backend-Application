package com.ey.ecalendar.service;

import com.ey.ecalendar.model.CalendarEvent;
import com.ey.ecalendar.repository.CalendarEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CalendarEventServiceTest {

    @Mock
    private CalendarEventRepository calendarEventRepository;

    @InjectMocks
    private CalendarEventService calendarEventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCalendarEvents() {
        calendarEventService.getAllCalendarEvents();
        verify(calendarEventRepository, times(1)).findAll();
    }

    @Test
    public void testGetCalendarEventById_Success() {
        CalendarEvent event = new CalendarEvent();
        event.setId(1L);
        when(calendarEventRepository.findById(1L)).thenReturn(Optional.of(event));
        CalendarEvent foundEvent = calendarEventService.getCalendarEventById(1L);
        assertNotNull(foundEvent);
        assertEquals(1L, foundEvent.getId());
    }

    @Test
    public void testGetCalendarEventById_Fail() {
        when(calendarEventRepository.findById(1L)).thenReturn(Optional.empty());
        CalendarEvent foundEvent = calendarEventService.getCalendarEventById(1L);
        assertNull(foundEvent);
    }

    @Test
    public void testAddCalendarEvent() {
        CalendarEvent event = new CalendarEvent();
        event.setTitle("Meeting");
        event.setDate(LocalDate.now());
        event.setType("Work");
        when(calendarEventRepository.save(event)).thenReturn(event);
        CalendarEvent savedEvent = calendarEventService.addCalendarEvent(event);
        assertNotNull(savedEvent);
        assertEquals("Meeting", savedEvent.getTitle());
        
    }

    @Test
    public void testUpdateCalendarEvent_Success() {
        CalendarEvent event = new CalendarEvent();
        event.setId(1L);
        event.setTitle("Old Title");
        when(calendarEventRepository.findById(1L)).thenReturn(Optional.of(event));

        CalendarEvent updatedEvent = new CalendarEvent();
        updatedEvent.setTitle("New Title");

        CalendarEvent result = calendarEventService.updateCalendarEvent(1L, updatedEvent);
        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        // This test should Fail.
    }

    @Test
    public void testUpdateCalendarEvent_Fail() {
        when(calendarEventRepository.findById(1L)).thenReturn(Optional.empty());
        CalendarEvent updatedEvent = new CalendarEvent();
        updatedEvent.setTitle("New Title");
        CalendarEvent result = calendarEventService.updateCalendarEvent(1L, updatedEvent);
        assertNull(result);
    }

    @Test
    public void testDeleteCalendarEvent() {
        CalendarEvent event = new CalendarEvent();
        event.setId(1L);
        when(calendarEventRepository.findById(1L)).thenReturn(Optional.of(event));
        calendarEventService.deleteCalendarEvent(1L);
        verify(calendarEventRepository, times(1)).deleteById(1L);
    }
}