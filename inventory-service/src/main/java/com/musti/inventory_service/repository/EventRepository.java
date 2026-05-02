package com.musti.inventory_service.repository;

import com.musti.inventory_service.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByName(final String name);
}
