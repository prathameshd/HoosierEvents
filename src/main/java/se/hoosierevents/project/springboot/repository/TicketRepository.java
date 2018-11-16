package se.hoosierevents.project.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.hoosierevents.project.model.Ticket;
import se.hoosierevents.project.model.User;

import java.util.List;

@Repository
@Transactional
public interface TicketRepository extends CrudRepository<Ticket, Long> {
	
	public List<Ticket> findAllByUser(User user);
	
}
