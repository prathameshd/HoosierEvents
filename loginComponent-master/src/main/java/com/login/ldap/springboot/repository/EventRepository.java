package com.login.ldap.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.login.ldap.model.Event;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EventRepository extends CrudRepository<Event, Long> {
	public List<Event> findAll();
	public Optional<Event> findById(Long id);
	public List<Event> findByEventTitle(String name);
}
