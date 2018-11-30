package se.hoosierevents.project.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.model.EventCategory;
import se.hoosierevents.project.model.User;

@Repository
@Transactional
public interface EventRepository extends CrudRepository<Event, Long> {
	final String EVENT_FIND_MAX_ID = "select max(event_id)+1 from events_master";
	final String EVENT_FIND_BY_CREATOR = "select event from Event event where event.createdBy = ?1";

	public List<Event> findAll();

	public Optional<Event> findById(Long id);

	public List<Event> findByEventTitle(String name);

	public List<Event> findAllByEventCategory(EventCategory eventcategory);

	public List<Event> findAllByLocation(String location);

	public Optional<Event> findAllByEventTitle(String event);
	
	public List<Event> findAllByCreatedBy(User user);

	@Query(value = EVENT_FIND_MAX_ID, nativeQuery = true)
	Long findMaxEventId();

	@Query(EVENT_FIND_BY_CREATOR)
	public List<Event> findEventsByCreator(User createdBy);

	public List<Event> findAllByCreatedBy(User user);
}
