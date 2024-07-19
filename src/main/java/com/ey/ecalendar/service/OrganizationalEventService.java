package com.ey.ecalendar.service;

import com.ey.ecalendar.model.OrganizationalEvent;
import com.ey.ecalendar.repository.OrganizationalEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationalEventService {

	@Autowired
    private OrganizationalEventRepository organizationalEventRepository;

    public List<OrganizationalEvent> getAllOrganizationalEvents() {
        return organizationalEventRepository.findAll();
    }

    public OrganizationalEvent getOrganizationalEventById(Long id) {
        return organizationalEventRepository.findById(id).orElse(null);
    }

    public OrganizationalEvent addOrganizationalEvent(OrganizationalEvent organizationalEvent) {
        return organizationalEventRepository.save(organizationalEvent);
    }

    public void deleteOrganizationalEvent(Long id) {
        organizationalEventRepository.deleteById(id);
    }

    public OrganizationalEvent updateOrganizationalEvent(Long id, OrganizationalEvent organizationalEventDetails) {
        OrganizationalEvent organizationalEvent = organizationalEventRepository.findById(id).orElse(null);
        if (organizationalEvent != null) {
            organizationalEvent.setTitle(organizationalEventDetails.getTitle());
            organizationalEvent.setDate(organizationalEventDetails.getDate());
            organizationalEvent.setEmployee(organizationalEventDetails.getEmployee());
            return organizationalEventRepository.save(organizationalEvent);
        } else {
            return null;
        }
    }
}