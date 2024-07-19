package com.ey.ecalendar.repository;

import com.ey.ecalendar.model.OrganizationalEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationalEventRepository extends JpaRepository<OrganizationalEvent, Long> {
}
