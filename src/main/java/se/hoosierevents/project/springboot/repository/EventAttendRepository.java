package se.hoosierevents.project.springboot.repository;
//import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

//import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.model.EventAttend;

public interface EventAttendRepository extends CrudRepository<EventAttend, Integer> {

//	public Optional<EventAttend> findByUserId(int userid);
}
