package se.hoosierevents.project.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.hoosierevents.project.model.Event;

@Repository
@Transactional
public interface EventRepository extends CrudRepository<Event, Long> {
	public List<Event> findAll();
	public Optional<Event> findById(Long id);
	public List<Event> findByEventTitle(String name);
}
