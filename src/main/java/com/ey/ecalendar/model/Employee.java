package com.ey.ecalendar.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String position;
    private String department;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CalendarEvent> calendarEvents;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrganizationalEvent> organizationalEvents;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<CalendarEvent> getCalendarEvents() {
		return calendarEvents;
	}

	public void setCalendarEvents(List<CalendarEvent> calendarEvents) {
		this.calendarEvents = calendarEvents;
	}

	public List<OrganizationalEvent> getOrganizationalEvents() {
		return organizationalEvents;
	}

	public void setOrganizationalEvents(List<OrganizationalEvent> organizationalEvents) {
		this.organizationalEvents = organizationalEvents;
	}

}