package se.hoosierevents.project.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.model.EventCategory;

@Repository
@Transactional
public interface EventRepository extends CrudRepository<Event, Long> {
	final String EVENT_FIND_MAX_ID = "select max(event_id)+1 from events_master";

	public List<Event> findAll();

	public Optional<Event> findById(Long id);

	public List<Event> findByEventTitle(String name);

	public List<Event> findAllByEventCategory(EventCategory eventcategory);

	public List<Event> findAllByLocation(String location);

	public Optional<Event> findAllByEventTitle(String event);

	@Query(value = EVENT_FIND_MAX_ID, nativeQuery = true)
	Long findMaxEventId();
}
