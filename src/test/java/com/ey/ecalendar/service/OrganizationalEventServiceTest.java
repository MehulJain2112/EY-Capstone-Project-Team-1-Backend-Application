package com.ey.ecalendar.service;

import com.ey.ecalendar.model.OrganizationalEvent;
import com.ey.ecalendar.repository.OrganizationalEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrganizationalEventServiceTest {

    @Mock
    private OrganizationalEventRepository organizationalEventRepository;

    @InjectMocks
    private OrganizationalEventService organizationalEventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrganizationalEvents() {
        organizationalEventService.getAllOrganizationalEvents();
        verify(organizationalEventRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrganizationalEventById_Success() {
        OrganizationalEvent event = new OrganizationalEvent();
        event.setId(1L);
        when(organizationalEventRepository.findById(1L)).thenReturn(Optional.of(event));
        OrganizationalEvent foundEvent = organizationalEventService.getOrganizationalEventById(1L);
        assertNotNull(foundEvent);
        assertEquals(1L, foundEvent.getId());
    }

    @Test
    public void testGetOrganizationalEventById_Fail() {
        when(organizationalEventRepository.findById(1L)).thenReturn(Optional.empty());
        OrganizationalEvent foundEvent = organizationalEventService.getOrganizationalEventById(1L);
        assertNull(foundEvent);
    }

    @Test
    public void testAddOrganizationalEvent() {
        OrganizationalEvent event = new OrganizationalEvent();
        event.setTitle("Conference");
        event.setDate(LocalDate.now());
        when(organizationalEventRepository.save(event)).thenReturn(event);
        OrganizationalEvent savedEvent = organizationalEventService.addOrganizationalEvent(event);
        assertNotNull(savedEvent);
        assertEquals("Conference", savedEvent.getTitle());
    }

    @Test
    public void testUpdateOrganizationalEvent_Success() {
        OrganizationalEvent event = new OrganizationalEvent();
        event.setId(1L);
        event.setTitle("Old Title");
        when(organizationalEventRepository.findById(1L)).thenReturn(Optional.of(event));

        OrganizationalEvent updatedEvent = new OrganizationalEvent();
        updatedEvent.setTitle("New Title");

        OrganizationalEvent result = organizationalEventService.updateOrganizationalEvent(1L, updatedEvent);
        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
    }

    @Test
    public void testUpdateOrganizationalEvent_Fail() {
        when(organizationalEventRepository.findById(1L)).thenReturn(Optional.empty());
        OrganizationalEvent updatedEvent = new OrganizationalEvent();
        updatedEvent.setTitle("New Title");
        OrganizationalEvent result = organizationalEventService.updateOrganizationalEvent(1L, updatedEvent);
        assertNull(result);
    }

    @Test
    public void testDeleteOrganizationalEvent() {
        OrganizationalEvent event = new OrganizationalEvent();
        event.setId(1L);
        when(organizationalEventRepository.findById(1L)).thenReturn(Optional.of(event));
        organizationalEventService.deleteOrganizationalEvent(1L);
        verify(organizationalEventRepository, times(1)).deleteById(1L);
    }
}