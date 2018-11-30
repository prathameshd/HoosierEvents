package se.hoosierevents.project.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.hoosierevents.project.model.TicketDetails;

@Repository
@Transactional
public interface TicketDetailsRepository extends CrudRepository<TicketDetails, Long>{

	//@Query("SELECT t FROM TicketDetails t where t.event_id = ?1 AND t.ticket_type = ?2")
    //public TicketDetails findByEventAndType(Long event_id, Long ticket_type);
}
