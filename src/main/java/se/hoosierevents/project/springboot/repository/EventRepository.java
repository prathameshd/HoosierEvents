package se.hoosierevents.project.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.model.EventCategory;

@Repository
@Transactional
public interface EventRepository extends CrudRepository<Event, Long> {
	public List<Event> findAll();
	public Optional<Event> findById(Long id);
	public List<Event> findByEventTitle(String name);
	public List<Event> findAllByEventCategory(EventCategory eventcategory);
	public List<Event> findAllByLocation(String location);
	public Optional<Event> findAllByEventTitle(String event);
}
